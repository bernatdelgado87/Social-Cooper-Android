package app.mistercooper.social.domain.registerLogin.usecase

import app.mistercooper.social.domain.registerLogin.model.LoginUserModel
import app.mistercooper.social.domain.registerLogin.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase (val userRepository: UserRepository) {
    operator fun invoke(params: LoginUserModel): Flow<Unit> {
        return flow { emit(userRepository.login(params)) }
    }

}