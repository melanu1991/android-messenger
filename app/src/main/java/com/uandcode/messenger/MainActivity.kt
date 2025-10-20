package com.uandcode.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uandcode.messenger.common.android.AndroidExceptionHandler
import com.uandcode.messenger.core.essentials.exceptions.ConnectionException
import com.uandcode.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.uandcode.messenger.core.essentials.logger.Logger
import com.uandcode.messenger.features.init.InitScreen
import com.uandcode.messenger.navigation.AppNavHost
import com.uandcode.messenger.ui.theme.MessengerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var exceptionToMessageMapper: ExceptionToMessageMapper

    @Inject
    lateinit var exceptionHandler: AndroidExceptionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MessengerTheme {
                AppNavHost(Modifier.fillMaxSize())
                exceptionHandler.ErrorDialog()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    App(modifier = Modifier.fillMaxSize().padding(innerPadding))
//                    exceptionHandler.ErrorDialog()
//                }
            }
        }
    }
}