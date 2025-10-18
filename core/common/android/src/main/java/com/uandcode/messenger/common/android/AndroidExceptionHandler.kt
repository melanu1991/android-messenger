package com.uandcode.messenger.common.android

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.uandcode.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.uandcode.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AndroidExceptionHandler @Inject constructor(
    private val exceptionToMessageMapper: ExceptionToMessageMapper,
    @ApplicationContext private val context: Context
) : ExceptionHandler {

    private val errorMessageState = mutableStateOf<String?>(null)
    override fun handleException(exception: Exception) {
        val message = exceptionToMessageMapper.getLocalizedMessage(exception)
        errorMessageState.value = message
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @Composable
    fun ErrorDialog() {
        errorMessageState.value?.let {
            AlertDialog(
                onDismissRequest = { errorMessageState.value = null },
                confirmButton = {
                    TextButton(
                        onClick = { errorMessageState.value = null }
                    ) {
                        Text("OK")
                    }
                },
                title = {
                    Text(text = it)
                }
            )
        }
    }
}