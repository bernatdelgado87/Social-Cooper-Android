package app.mistercooper.social.data.home.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class PublishLikeBodyDTO(
    val postReference: Long,
    val like: Boolean
)
