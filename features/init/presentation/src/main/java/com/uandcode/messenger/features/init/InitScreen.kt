package com.uandcode.messenger.features.init

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.elveum.container.Container
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.MediumVerticalSpacer
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.components.ProgressButton
import com.uandcode.messenger.features.init.State.KeyFeature

@Composable
fun InitScreen(modifier: Modifier = Modifier) {
    val viewModel: InitViewModel = hiltViewModel()
    val container: Container<State> by viewModel.stateFlow.collectAsState()

    ContainerView(container = container, modifier = Modifier.fillMaxSize()) {
        InitContent(it, modifier) {
            viewModel.letsGo()
        }
    }
}

@Composable
fun InitContent(state: State, modifier: Modifier = Modifier, onLetsGoAction: () -> Unit) {
    Column(modifier
        .fillMaxSize()
        .padding(Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val keyFeature = state.keyFeature
        Text(
            text = "Init Screen. Key feature: ${keyFeature.title}",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        MediumVerticalSpacer()
        Text(
            text = "Initialization status: ${keyFeature.description}",
            textAlign = TextAlign.Center
        )
        MediumVerticalSpacer()
        ProgressButton(isInProgress = state.isCheckAuthInProgress, text = "Let's go", onClick = onLetsGoAction)
    }
}

@Preview
@Composable
private fun InitContentPreview() {
    InitContent(
        State(
            keyFeature = KeyFeature(
                title = "Sample Feature",
                description = "This is a sample feature for preview purposes."
            ),
            isCheckAuthInProgress = false
        ),
        onLetsGoAction = {}
    )
}