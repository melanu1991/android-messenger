package com.uandcode.messenger.features.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.elveum.container.Container
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.MediumVerticalSpacer
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.components.ProgressButton

@Composable
fun SigninScreen(modifier: Modifier = Modifier) {
    val viewModel: SigninViewModel = hiltViewModel()
    val container: Container<SigninState> by viewModel.stateFlow.collectAsState()

    ContainerView(container = container, modifier = Modifier.fillMaxSize()) {
        SigninContent(it, modifier) { email, password ->
            viewModel.signIn(email, password)
        }
    }
}

@Composable
fun SigninContent(state: SigninState, modifier: Modifier = Modifier, onSignIn: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier
        .fillMaxSize()
        .padding(Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Sign In",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        MediumVerticalSpacer()

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        MediumVerticalSpacer()
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())

        MediumVerticalSpacer()

        ProgressButton(isInProgress = state.isSigningIn, text = "Sign in") {
            onSignIn(email, password)
        }
    }
}

@Preview
@Composable
private fun SigninContentPreview() {
    SigninContent(
        SigninState(signinInfo = SigninState.SigninInfo("Welcome", "Please sign in"), isSigningIn = false),
        onSignIn = { _, _ -> }
    )
}
