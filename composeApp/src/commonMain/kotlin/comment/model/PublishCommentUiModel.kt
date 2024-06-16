package app.mistercooper.ui_comment_shared.model

import app.mistercooper.social.domain.comment.model.CommentWrapperModel


data class PublishCommentUiModel(
    val isError: Boolean = false,
    val isLoadingComments: Boolean = false,
    val isLoadingPublish: Boolean = false,
    val commentWrapper: CommentWrapperModel? = null
)