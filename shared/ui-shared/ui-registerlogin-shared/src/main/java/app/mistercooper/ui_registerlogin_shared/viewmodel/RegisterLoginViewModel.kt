package app.mistercooper.ui_registerlogin_shared.viewmodel

import app.mistercooper.domain.register_login.model.LoginUserModel
import app.mistercooper.domain.register_login.model.RegisterUserModel
import app.mistercooper.domain.register_login.usecase.LoginUseCase
import app.mistercooper.domain.register_login.usecase.RegisterUserUseCase
import app.mistercooper.ui_registerlogin_shared.model.RegisterLoginUiModel
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File

class RegisterLoginViewModel (
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUseCase
    ) : KMMViewModel() {

    private val _registerLoginUiModelState = MutableStateFlow(
        RegisterLoginUiModel()
    )
    val registerLoginState = _registerLoginUiModelState.asStateFlow()

    fun registerUser(email: String, userName: String, password: String, file: File) {
        viewModelScope.coroutineScope.launch {
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
        viewModelScope.coroutineScope.launch {
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