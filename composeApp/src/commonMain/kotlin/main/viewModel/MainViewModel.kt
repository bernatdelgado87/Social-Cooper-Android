package main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.social.domain.main.usecase.IsUserRegisteredUseCase
import app.mistercooper.social.domain.registerLogin.model.RegisterUserModel
import app.mistercooper.social.domain.registerLogin.usecase.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(
    private val isUserRegisteredUseCase: IsUserRegisteredUseCase,
    private val registerUseCase: RegisterUserUseCase
) : ViewModel() {
    private val _isRegisteredState: MutableStateFlow<Boolean?> = MutableStateFlow(
        null
    )
    val isRgisteredState = _isRegisteredState.asStateFlow()

    init {
        isRegistered()
    }

    fun isRegistered() {
        viewModelScope.launch {
            isUserRegisteredUseCase.run()
                .catch {
                    it.printStackTrace()
                }.collect { response ->
                    _isRegisteredState.emit(response)
                }
        }
    }

    fun register(registerUserModel: RegisterUserModel) {
        registerUserModel.apikey?.let {
            viewModelScope.launch {
                registerUseCase(registerUserModel)
                    .catch {
                        it.printStackTrace()
                    }.collect {
                        _isRegisteredState.emit(true)
                    }
            }
        } ?: {
            //todo error!!! apikey not found
        }
    }

}