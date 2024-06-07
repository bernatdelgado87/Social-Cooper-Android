package app.mistercooper.domain_shared_common.user.usecase

import app.mistercooper.domain_shared_common.user.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IsUserRegisteredUseCase (val userRepository: MainRepository) {
    operator fun invoke(): Flow<Boolean> {
        return flow { emit(userRepository.isUserRegistered()) }
    }

}