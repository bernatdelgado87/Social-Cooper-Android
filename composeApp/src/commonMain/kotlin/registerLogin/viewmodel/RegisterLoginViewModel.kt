package registerLogin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.social.domain.registerLogin.model.LoginUserModel
import app.mistercooper.social.domain.registerLogin.model.RegisterUserModel
import app.mistercooper.social.domain.registerLogin.usecase.LoginUseCase
import app.mistercooper.social.domain.registerLogin.usecase.RegisterUserUseCase
import app.mistercooper.ui_registerlogin_shared.model.RegisterLoginUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RegisterLoginViewModel (
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUseCase
    ) : ViewModel() {

    private val _registerLoginUiModelState = MutableStateFlow(
        RegisterLoginUiModel()
    )
    val registerLoginState = _registerLoginUiModelState.asStateFlow()

    fun registerUser(email: String, userName: String, password: String, file: ByteArray) {
        viewModelScope.launch {
            registerUserUseCase(
                RegisterUserModel(
                    name = userName,
                    email = email,
                    password = password,
                    imageProfile = file
                )
            )
                .catch {
                    it.printStackTrace()
                    _registerLoginUiModelState.emit(RegisterLoginUiModel(error = true))

                }.collect { response ->
                    _registerLoginUiModelState.emit(RegisterLoginUiModel(registerLoginSuccess = true))
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(
                LoginUserModel(
                    email = email,
                    password = password
                )
            )
                .catch {
                    it.printStackTrace()
                    _registerLoginUiModelState.emit(RegisterLoginUiModel(error = true))

                }.collect { response ->
                    _registerLoginUiModelState.emit(RegisterLoginUiModel(registerLoginSuccess = true))
                }
        }
    }


}