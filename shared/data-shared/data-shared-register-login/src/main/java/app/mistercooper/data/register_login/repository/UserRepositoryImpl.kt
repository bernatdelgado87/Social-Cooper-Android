package app.mistercooper.data.register_login.repository

import app.mistercooper.data.register_login.remote.api.RegisterLoginApi
import app.mistercooper.data.register_login.remote.mapper.toDTO
import app.mistercooper.domain.register_login.model.LoginUserModel
import app.mistercooper.domain.register_login.model.RegisterUserModel
import app.mistercooper.domain.register_login.repository.UserRepository
import app.mistercooper.domain_shared_common.arch.model.GlobalFailure
import com.jetbrains.kmpapp.local.KeyValueStorageInterface

class UserRepositoryImpl(
    private val apiRemote: RegisterLoginApi,
    private val localUserDataSource: KeyValueStorageInterface
) : UserRepository {
    override suspend fun registerUser(registerUserModel: RegisterUserModel) {
        try {
            val response = apiRemote.registerUser(
                name = registerUserModel.name,
                email = registerUserModel.email,
                password = registerUserModel.password,
                imageProfile = registerUserModel.imageProfile
            )
                    localUserDataSource.token = response.apikey
                    return

            } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }

    override fun getApiKey() = localUserDataSource.token

    override suspend fun login(loginUserModel: LoginUserModel) {
        try {
            val response = apiRemote.login(loginUserModel.toDTO())
                response.let {
                    localUserDataSource.token = it.apikey
                    return
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }

}