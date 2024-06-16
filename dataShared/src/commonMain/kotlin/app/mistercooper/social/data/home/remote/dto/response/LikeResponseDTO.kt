package app.mistercooper.social.data.home.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class LikeResponseDTO (
    val userId: Int?,
    val postReference: Int?,
    val multimediaModel: MultimediaDTO? = null
)