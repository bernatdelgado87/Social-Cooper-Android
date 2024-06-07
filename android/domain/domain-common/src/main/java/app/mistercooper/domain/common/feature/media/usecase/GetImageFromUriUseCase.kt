package app.mistercooper.domain.common.feature.media.usecase

import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class GetImageFromUriUseCase (private val mediaRepository: MediaRepository) {
    operator fun invoke(params: String): Flow<File> {
        return flow {
            emit(mediaRepository.getSavedImageByUri(params))
        }
    }
}