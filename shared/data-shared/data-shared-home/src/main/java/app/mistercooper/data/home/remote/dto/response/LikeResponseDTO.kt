package app.mistercooper.data.home.remote.dto.response

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class LikeResponseDTO (
    val userId: Int?,
    val postReference: Int?,
    val multimediaModel: MultimediaDTO? = null
)