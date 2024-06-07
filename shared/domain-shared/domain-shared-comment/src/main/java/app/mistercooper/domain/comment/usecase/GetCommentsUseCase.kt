package app.mistercooper.domain.comment.usecase

import app.mistercooper.domain.comment.model.CommentWrapperModel
import app.mistercooper.domain.comment.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCommentsUseCase (val socialRepository: CommentRepository) {
    operator fun invoke(params: GetCommentsParams): Flow<CommentWrapperModel> {
        return flow { emit(socialRepository.getComments(params.postId)) }
    }

    data class GetCommentsParams(val postId: Long)
}