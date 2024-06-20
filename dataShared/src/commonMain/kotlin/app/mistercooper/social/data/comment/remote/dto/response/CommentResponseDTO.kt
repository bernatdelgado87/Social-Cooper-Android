package app.mistercooper.social.data.comment.remote.dto.response

import app.mistercooper.social.data.home.remote.dto.response.MultimediaDTO
import com.jetbrains.kmpapp.remote.dto.UserDTO
import kotlinx.serialization.Serializable

@Serializable
data class CommentResponseDTO (
    val multimediaModel: MultimediaDTO,
    val userImageUrl: String,
    val comments: List<CommentDTO>
)
@Serializable
data class CommentDTO (
    val id: Int,
    val content: String,
    val createTime: String,
    val user: UserDTO,
    val commentReferent: Int? = null
)