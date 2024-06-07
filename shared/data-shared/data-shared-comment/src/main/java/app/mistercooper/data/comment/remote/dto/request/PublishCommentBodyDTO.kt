package app.mistercooper.data.comment.remote.dto.request

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class PublishCommentBodyDTO(
    val postReference: Long,
    val text: String
)
