package app.mistercooper.social.data.user

import app.mistercooper.data.register_login.remote.mapper.toDTO
import app.mistercooper.domain_shared_common.arch.model.GlobalFailure
import app.mistercooper.social.data.user.remote.api.RegisterLoginApi
import app.mistercooper.social.domain.registerLogin.model.LoginUserModel
import app.mistercooper.social.domain.registerLogin.model.RegisterUserModel
import app.mistercooper.social.domain.registerLogin.repository.UserRepository
import com.jetbrains.kmpapp.local.KeyValueStorageInterface
import io.ktor.util.date.getTimeMillis

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
                imageProfile = registerUserModel.imageProfile,
                filename = registerUserModel.name + getTimeMillis()
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