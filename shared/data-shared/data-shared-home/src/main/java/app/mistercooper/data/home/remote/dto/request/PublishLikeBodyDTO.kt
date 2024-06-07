package app.mistercooper.data.home.remote.dto.request

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class PublishLikeBodyDTO(
    val postReference: Long,
    val like: Boolean
)
