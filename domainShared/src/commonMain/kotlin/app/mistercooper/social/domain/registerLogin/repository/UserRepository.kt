package app.mistercooper.social.domain.registerLogin.repository

import app.mistercooper.social.domain.registerLogin.model.LoginUserModel
import app.mistercooper.social.domain.registerLogin.model.RegisterUserModel
import app.mistercooper.social.domain.registerLogin.model.UpdateUserModel

interface UserRepository {
    suspend fun registerUser(registerUserModel: RegisterUserModel)
    suspend fun updateUser(registerUserModel: UpdateUserModel)
    suspend fun login(loginUserModel: LoginUserModel)

    fun getApiKey(): String?

}