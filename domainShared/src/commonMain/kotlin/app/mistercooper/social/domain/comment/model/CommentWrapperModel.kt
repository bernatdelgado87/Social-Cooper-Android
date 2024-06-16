package app.mistercooper.social.domain.comment.model

data class CommentWrapperModel(
    val myImageUrl: String,
    val comments: List<CommentModel>
)