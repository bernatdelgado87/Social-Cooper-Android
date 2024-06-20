package app.mistercooper.social.data.home.remote.dto.response

import com.jetbrains.kmpapp.remote.dto.UserDTO
import kotlinx.serialization.Serializable

@Serializable
data class MultimediaFeedDTO(
    val multimediaModel: List<MultimediaDTO>,
    val morePages: Boolean
)

@Serializable
data class MultimediaDTO (
    val id: Long,
    val user: UserDTO,
    val description: String? = null,
    val relativeUrl: String,
    val absoluteUrl: String,
    val numberOfLikes: Int = 0,
    val numberOfComments: Int = 0,
    val likes: List<LikeSimplifiedDTO>? = null,
    val hasLiked: Boolean)

@Serializable
data class LikeSimplifiedDTO (
    val userId: String?,
    val postReference: Int?)
