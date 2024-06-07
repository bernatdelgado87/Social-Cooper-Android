package app.mistercooper.domain.register_login.repository

interface UserRepository {
    suspend fun registerUser(registerUserModel: app.mistercooper.domain.register_login.model.RegisterUserModel)
    suspend fun login(loginUserModel: app.mistercooper.domain.register_login.model.LoginUserModel)

    fun getApiKey(): String?

}