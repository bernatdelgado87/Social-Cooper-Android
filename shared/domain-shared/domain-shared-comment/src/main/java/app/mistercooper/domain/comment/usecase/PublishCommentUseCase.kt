package app.mistercooper.domain.comment.usecase

import app.mistercooper.domain.comment.model.CommentWrapperModel
import app.mistercooper.domain.comment.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PublishCommentUseCase (val socialRepository: CommentRepository) {
    operator fun invoke(params: PublishCommentParams): Flow<CommentWrapperModel> {
        return flow { emit(socialRepository.publishComment(params.comment, params.postId, params.commentReferentId)) }
    }

    data class PublishCommentParams(val comment: String, val postId: Long, val commentReferentId: Int? = null)
}