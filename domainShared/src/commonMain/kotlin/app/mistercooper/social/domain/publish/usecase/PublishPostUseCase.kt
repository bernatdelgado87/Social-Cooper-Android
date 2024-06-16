package app.mistercooper.social.domain.publish.usecase

import app.mistercooper.social.domain.publish.repository.PublishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PublishPostUseCase constructor(val socialRepository: PublishRepository) {
    operator fun invoke(params: PublishPostParams): Flow<Unit> {
        return flow { emit(socialRepository.publishPost(params.text, params.byteArray, params.username)) }
    }

    data class PublishPostParams(val text: String, val byteArray: ByteArray, val username: String)
}