package app.mistercooper.social.data.comment.remote.api

import app.mistercooper.social.data.comment.remote.dto.request.PublishCommentBodyDTO
import app.mistercooper.social.data.comment.remote.dto.response.CommentResponseDTO
import com.jetbrains.kmpapp.remote.api.CommonApi.Companion.SOCIAL_API_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

interface CommentRemoteApi {
    suspend fun getComments(postId: Long, n: Int, offset: Long): CommentResponseDTO
    suspend fun publishComment(postId: Long, text: String): CommentResponseDTO
}

class CommentApiImpl(private val client: HttpClient) : CommentRemoteApi {
    override suspend fun getComments(postId: Long, n: Int, offset: Long): CommentResponseDTO {
        return try {
            client.get(SOCIAL_API_URL + "detail") {
                url {
                    parameters.append("postId", postId.toString())
                    parameters.append("n", n.toString())
                    parameters.append("offset", offset.toString())
                }
            }.call.response.body<CommentResponseDTO>()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun publishComment(postId: Long, text: String): CommentResponseDTO {
        return try {
            client.post(SOCIAL_API_URL + "comment") {
                setBody(PublishCommentBodyDTO(postId, text))
            }.call.response.body<CommentResponseDTO>()
        } catch (e: Exception) {
            throw e
        }
    }

}
