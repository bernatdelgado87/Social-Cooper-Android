package app.mistercooper.social.domain.registerLogin.repository

import app.mistercooper.social.domain.registerLogin.model.LoginUserModel
import app.mistercooper.social.domain.registerLogin.model.RegisterUserModel

interface UserRepository {
    suspend fun registerUser(registerUserModel: RegisterUserModel)
    suspend fun login(loginUserModel: LoginUserModel)

    fun getApiKey(): String?

}