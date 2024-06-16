package app.mistercooper.social.data.comment.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class PublishCommentBodyDTO(
    val postReference: Long,
    val text: String
)
