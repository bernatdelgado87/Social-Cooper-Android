package app.mistercooper.social.domain.home.model

data class FeedModel(
    val postModels: List<PostModel>,
    val hasMorePages: Boolean
)