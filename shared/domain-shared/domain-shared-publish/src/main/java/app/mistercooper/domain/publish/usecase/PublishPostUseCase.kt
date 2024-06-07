package app.mistercooper.social.domain.feature.publish.usecase

import app.mistercooper.domain.publish.repository.PublishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class PublishPostUseCase constructor(val socialRepository: PublishRepository) {
    operator fun invoke(params: PublishPostParams): Flow<Unit> {
        return flow { emit(socialRepository.publishPost(params.text, params.file)) }
    }

    data class PublishPostParams(val text: String, val file: File)
}