package app.mistercooper.social.domain.main.usecase

import app.mistercooper.social.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class IsFullRegisteredUseCase (val userRepository: MainRepository) {
    fun run(): Flow<Boolean> {
        return flow { emit(userRepository.isFullRegistered()) }
    }

}