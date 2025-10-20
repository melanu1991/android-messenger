package com.uandcode.messenger.features.init

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elveum.container.Container
import com.elveum.container.map
import com.elveum.container.subject.ContainerConfiguration
import com.elveum.container.subject.LazyFlowSubject
import com.uandcode.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.uandcode.messenger.features.init.State.KeyFeature
import com.uandcode.messenger.features.init.domain.IsAuthorizedUseCase
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

//interface GetKeyFeatureUseCase {
//    operator fun invoke(): Flow<Container<State.KeyFeature>>
//}

class GetKeyFeatureUseCase @Inject constructor() {
    fun invoke(): Flow<Container<KeyFeature>> {
        // Simulate fetching key feature data
//        return flow {
//            kotlinx.coroutines.delay(1000) // Simulate network delay
//            val keyFeature = State.KeyFeature(
//                title = "Awesome Feature",
//                description = "This feature enhances user experience."
//            )
//            emit(successContainer(keyFeature))
//        }
        return LazyFlowSubject.create {
            delay(1000) // Simulate network delay
            val keyFeature = KeyFeature(
                title = "Awesome Feature",
                description = "This feature enhances user experience."
            )
//            throw ConnectionException()
            emit(keyFeature)
        }.listen(configuration = ContainerConfiguration(emitReloadFunction = true))
    }
}

@HiltViewModel
class InitViewModel @Inject constructor(
    private val getKeyFeatureUseCase: GetKeyFeatureUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val router: InitRouter,
    private val exceptionHandler: ExceptionHandler
): ViewModel() {
//    val stateFlow: StateFlow<Container<State>> = getKeyFeatureUseCase
//        .invoke()
//        .containerMap { keyFeature -> State(keyFeature, false) }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), Container.Pending)

    private val vmStateFlow = MutableStateFlow(ViewModelState(false))
    val stateFlow: StateFlow<Container<State>> = combine(
        // pass the Flow produced by the use case (invoke()) — combine expects Flow parameters
        getKeyFeatureUseCase.invoke(),
        vmStateFlow
    ) {
        keyFeatureContainer, vmState ->
        keyFeatureContainer.map { keyFeature ->
            State(
                keyFeature = keyFeature,
                isCheckAuthInProgress = vmState.isOperationAuthInProgress
            )
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), Container.Pending)

//    private val _effectsFlow = MutableStateFlow(Effects())
//    val effectsFlow: StateFlow<Effects> = _effectsFlow

    fun letsGo()  {
        viewModelScope.launch {
            try {
                vmStateFlow.update {it.copy(isOperationAuthInProgress = true) }
                val isAuthorized = isAuthorizedUseCase.invoke()
                if (isAuthorized) {

                } else {
                    router.launchSignIn()
//                    _effectsFlow.update { it.copy(launchSignInScreen = Unit) }
                }
            } catch (e: Exception) {
                ensureActive()
                exceptionHandler.handleException(e)
                vmStateFlow.update {it.copy(isOperationAuthInProgress = false) }
            }
        }
    }

//    fun onLaunchSignInEffectProcessed() {
//        _effectsFlow.update { it.copy(launchSignInScreen = null) }
//    }

//    data class Effects(
//        val launchSignInScreen: Unit? = null
//    )
}

data class State(
    val keyFeature: KeyFeature = KeyFeature("Unknown", "not initialized"),
    val isCheckAuthInProgress: Boolean
) {
    data class KeyFeature(val title: String, val description: String)
}

private data class ViewModelState(
    val isOperationAuthInProgress: Boolean
)