package app.mistercooper.domain.register_login.usecase

import app.mistercooper.domain.register_login.model.RegisterUserModel
import app.mistercooper.domain.register_login.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase (val userRepository: UserRepository){
    operator fun invoke(params: RegisterUserModel): Flow<Unit> {
        return flow { emit(userRepository.registerUser(params)) }
    }

}