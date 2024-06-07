package app.mistercooper.ui_home_shared.model

import app.mistercooper.domain.home.model.PostModel


data class HomeUiModel(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val postModels: List<PostModel>? = null
)