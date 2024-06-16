package app.mistercooper.social.domain.home.model

import app.mistercooper.domain_shared_common.user.model.UserModel


data class PostModel(
    val id: Long,
    val user: UserModel,
    val description: String?,
    val imageUrl: String,
    val totalLikes: Int,
    val totalComments: Int,
    val hasLiked: Boolean
)