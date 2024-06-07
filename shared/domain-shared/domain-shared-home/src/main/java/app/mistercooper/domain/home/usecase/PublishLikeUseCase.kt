package app.mistercooper.domain.home.usecase

import app.mistercooper.domain.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PublishLikeUseCase (private val socialRepository: HomeRepository) {
    operator fun invoke(params: PublishLikeParams): Flow<Unit> {
        return flow { emit(socialRepository.publishLike(params.postId, params.like)) }
    }

    data class PublishLikeParams(val postId: Long, val like: Boolean)
}