package feed.model

import app.mistercooper.social.domain.home.model.PostModel


data class HomeUiModel(
    val isError: Boolean = false,
    val isFullRegistered: Boolean? = null,
    val isLoading: Boolean = false,
    val postModels: List<PostModel>? = null
)