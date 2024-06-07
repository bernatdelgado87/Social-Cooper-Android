package app.mistercooper.domain.common.feature.media.usecase

import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMediaImagesFromDeviceUseCase (private val mediaRepository: MediaRepository) {
    operator fun invoke(): Flow<Unit> {
        return flow {
            emit(mediaRepository.getSavedImages())
        }
    }
}