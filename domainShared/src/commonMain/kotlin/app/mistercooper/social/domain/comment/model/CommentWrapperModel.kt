package app.mistercooper.social.domain.comment.model

import app.mistercooper.social.domain.home.model.PostModel

data class CommentWrapperModel(
    val post: PostModel,
    val myImageUrl: String,
    val comments: List<CommentModel>
)