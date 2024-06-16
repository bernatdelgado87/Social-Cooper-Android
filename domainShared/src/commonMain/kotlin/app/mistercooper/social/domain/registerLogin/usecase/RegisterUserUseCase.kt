package app.mistercooper.social.domain.registerLogin.usecase

import app.mistercooper.social.domain.registerLogin.model.RegisterUserModel
import app.mistercooper.social.domain.registerLogin.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase (val userRepository: UserRepository){
    operator fun invoke(params: RegisterUserModel): Flow<Unit> {
        return flow { emit(userRepository.registerUser(params)) }
    }

}