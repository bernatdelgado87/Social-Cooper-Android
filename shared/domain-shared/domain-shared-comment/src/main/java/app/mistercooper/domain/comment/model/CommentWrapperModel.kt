package app.mistercooper.domain.comment.model

data class CommentWrapperModel(
    val myImageUrl: String,
    val comments: List<CommentModel>
)