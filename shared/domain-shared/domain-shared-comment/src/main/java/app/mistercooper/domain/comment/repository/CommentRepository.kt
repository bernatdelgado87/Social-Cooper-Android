package app.mistercooper.domain.comment.repository

import app.mistercooper.domain.comment.model.CommentWrapperModel

interface CommentRepository {
    suspend fun publishComment(comment: String, postId: Long, commentReferentId: Int? = null): CommentWrapperModel
    suspend fun getComments(postId: Long): CommentWrapperModel
}