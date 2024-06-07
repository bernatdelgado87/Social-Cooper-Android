package app.mistercooper.data.home.remote.dto.response

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
data class MultimediaDTO (
    val id: Long,
    val user: com.jetbrains.kmpapp.remote.dto.UserDTO,
    val description: String? = null,
    val relativeUrl: String,
    val absoluteUrl: String,
    val numberOfLikes: Int = 0,
    val numberOfComments: Int = 0,
    val likes: List<LikeSimplifiedDTO>? = null,
    val hasLiked: Boolean)

@Serializable
@Keep
data class LikeSimplifiedDTO (
    val userId: String?,
    val postReference: Int?)
