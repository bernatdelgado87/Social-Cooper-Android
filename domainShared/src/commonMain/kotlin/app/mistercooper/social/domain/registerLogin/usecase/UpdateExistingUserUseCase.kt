package app.mistercooper.social.domain.registerLogin.usecase

import app.mistercooper.social.domain.registerLogin.model.UpdateUserModel
import app.mistercooper.social.domain.registerLogin.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateExistingUserUseCase (val userRepository: UserRepository){
    operator fun invoke(params: UpdateUserModel): Flow<Unit> {
        return flow { emit(userRepository.updateUser(params)) }
    }

}