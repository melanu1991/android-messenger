package com.uandcode.messenger.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elveum.container.Container
import com.elveum.container.map
import com.elveum.container.subject.ContainerConfiguration
import com.elveum.container.subject.LazyFlowSubject
import com.uandcode.messenger.core.essentials.exceptions.handler.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetSigninInfoUseCase @Inject constructor() {
    fun invoke(): Flow<Container<SigninState.SigninInfo>> {
        return LazyFlowSubject.create {
            delay(300) // small delay for demo
            val info = SigninState.SigninInfo(title = "Welcome back", subtitle = "Please sign in to continue")
            emit(info)
        }.listen(configuration = ContainerConfiguration(emitReloadFunction = true))
    }
}

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val getSigninInfoUseCase: GetSigninInfoUseCase,
    private val exceptionHandler: ExceptionHandler
) : ViewModel() {

    private val vmStateFlow = MutableStateFlow(ViewModelState(isSigningIn = false))

    val stateFlow: StateFlow<Container<SigninState>> = combine(
        getSigninInfoUseCase.invoke(),
        vmStateFlow
    ) { signinInfoContainer, vmState ->
        signinInfoContainer.map { signinInfo ->
            SigninState(signinInfo = signinInfo, isSigningIn = vmState.isSigningIn)
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), Container.Pending)

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                vmStateFlow.update { it.copy(isSigningIn = true) }
                // Simulate network call
                delay(800)
                // Normally you'd call a use case here and react to result
                vmStateFlow.update { it.copy(isSigningIn = false) }
            } catch (e: Exception) {
                ensureActive()
                exceptionHandler.handleException(e)
                vmStateFlow.update { it.copy(isSigningIn = false) }
            }
        }
    }
}

data class SigninState(
    val signinInfo: SigninInfo = SigninInfo("Unknown", ""),
    val isSigningIn: Boolean
) {
    data class SigninInfo(val title: String, val subtitle: String)
}

private data class ViewModelState(val isSigningIn: Boolean)
