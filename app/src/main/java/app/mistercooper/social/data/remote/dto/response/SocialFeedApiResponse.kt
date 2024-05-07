package app.mistercooper.social.data.remote.dto.response

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class MultimediaFeedDTO(
    val multimediaModel: List<MultimediaDTO>,
    val morePages: Boolean
)

@Serializable
@Keep
data class LikeSimplifiedDTO (
    val userId: String?,
    val postReference: Int?)

@Serializable
@Keep
data class MultimediaDTO (
    val id: Long,
    val userId: Int,
    val description: String? = null,
    val relativeUrl: String,
    val absoluteUrl: String,
    val numberOfLikes: Int = 0,
    val likes: List<LikeSimplifiedDTO>? = null)


