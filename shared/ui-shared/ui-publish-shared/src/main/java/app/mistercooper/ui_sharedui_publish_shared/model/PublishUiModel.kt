package app.mistercooper.ui_sharedui_publish_shared.model

data class PublishUiModel(
    val loading: Boolean = false,
    val error: Boolean = false,
    val postPublishedSuccess: Boolean = false
)
