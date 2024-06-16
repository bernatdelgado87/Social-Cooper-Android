package app.mistercooper.domain_shared_common.user.model

data class UserModel(
    val id: Int,
    val userName: String? = null,
    val imageProfileUrl: String? = null,
)
