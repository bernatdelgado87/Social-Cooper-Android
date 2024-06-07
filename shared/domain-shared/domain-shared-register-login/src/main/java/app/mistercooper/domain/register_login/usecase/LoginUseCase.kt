package app.mistercooper.domain.register_login.usecase

import app.mistercooper.domain.register_login.model.LoginUserModel
import app.mistercooper.domain.register_login.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase (val userRepository: UserRepository) {
    operator fun invoke(params: LoginUserModel): Flow<Unit> {
        return flow { emit(userRepository.login(params)) }
    }

}