package app.mistercooper.data.comment.repository

import app.mistercooper.social.data.comment.remote.api.CommentRemoteApi
import app.mistercooper.social.data.comment.remote.dto.mapper.toModel
import app.mistercooper.domain_shared_common.arch.model.GlobalFailure
import app.mistercooper.social.domain.comment.model.CommentWrapperModel
import app.mistercooper.social.domain.comment.repository.CommentRepository

class CommentRepositoryImpl(private val apiRemote: CommentRemoteApi) :
    CommentRepository {

    override suspend fun publishComment(
        comment: String,
        postId: Long,
        commentReferentId: Int?
    ): CommentWrapperModel {
        try {
            val response = apiRemote.publishComment(
                postId = postId,
                text = comment
            )
            return response.toModel()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }

    override suspend fun getComments(postId: Long): CommentWrapperModel {
        try {
            val response = apiRemote.getComments(
                postId = postId,
                n = 20,
                offset = 0
            )
            return response.toModel()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }

}