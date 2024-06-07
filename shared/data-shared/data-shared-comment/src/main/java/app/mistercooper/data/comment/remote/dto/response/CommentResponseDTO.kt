package app.mistercooper.data.comment.remote.dto.response

import androidx.annotation.Keep
import com.jetbrains.kmpapp.remote.dto.UserDTO
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class CommentResponseDTO (
    val imageUrl: String,
    val comments: List<CommentDTO>
)
@Serializable
@Keep
data class CommentDTO (
    val id: Int,
    val content: String,
    val createTime: String,
    val user: UserDTO,
    val commentReferent: Int? = null
)