package app.mistercooper.domain.home.model

data class FeedModel(
    val postModels: List<PostModel>,
    val hasMorePages: Boolean
)