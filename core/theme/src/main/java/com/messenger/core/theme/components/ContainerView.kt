package com.messenger.core.theme.components

import android.R.attr.value
import android.R.id.message
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.elveum.container.Container
import com.elveum.container.ContainerMapperScope
import com.elveum.container.errorContainer
import com.elveum.container.pendingContainer
import com.elveum.container.successContainer
import com.uandcode.messenger.core.essentials.exceptions.ConnectionException
import com.uandcode.messenger.core.essentials.exceptions.mapper.EmptyExceptionToMessageMapper
import com.uandcode.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper

@Composable
fun <T> ContainerView(
    container: Container<T>,
    modifier: Modifier = Modifier,
    exceptionToMessageMapper: ExceptionToMessageMapper = EmptyExceptionToMessageMapper(),
    onTryAgainAction: () -> Unit = {},
    content: @Composable BoxAndMapperScope.(T) -> Unit,
) {
    Box(modifier) {
        container.fold(
            onPending = {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            },
            onError = { error ->
                // You can customize error handling here
                val message = exceptionToMessageMapper.getLocalizedMessage(error)
                ErrorContainerView(message) {
                    reload(silently = false)
                }
            },
            onSuccess = { data ->
                val combinedScope = BoxAndMapperScopeImpl(this@Box, this)
                combinedScope.content(data)
            }
        )
    }
}

interface BoxAndMapperScope: BoxScope, ContainerMapperScope

private class BoxAndMapperScopeImpl(
    boxScope: BoxScope,
    mapperScope: ContainerMapperScope
): BoxAndMapperScope, BoxScope by boxScope, ContainerMapperScope by mapperScope

@Preview
@Composable
private fun SuccessContainerView() {
    ContainerView(
        container = successContainer(value = "Test test test")
    ) { value ->
        Text(text = value)
    }
}

@Preview
@Composable
private fun PendingContainerView() {
    ContainerView<String>(
        container = pendingContainer()
    ) { value ->
        Text(text = value)
    }
}

@Preview
@Composable
private fun ErrorContainerView() {
    ContainerView<String>(
        container = errorContainer(ConnectionException())
    ) { value ->
        Text(text = value)
    }
}

@Composable
fun BoxScope.ErrorContainerView(message: String, onTryAgainAction: () -> Unit) {
    Column(modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Error: $message", textAlign = TextAlign.Center)

        Button(onClick = { onTryAgainAction() }) {
            Text(text = "Retry")
        }
    }
}