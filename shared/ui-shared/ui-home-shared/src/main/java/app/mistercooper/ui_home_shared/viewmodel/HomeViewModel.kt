package app.mistercooper.ui_home_shared.viewmodel

import app.mistercooper.domain.home.usecase.GetFeedUseCase
import app.mistercooper.domain.home.usecase.PublishLikeUseCase
import app.mistercooper.ui_home_shared.model.HomeUiModel
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class HomeViewModel (
    private val getFeedUseCase: GetFeedUseCase,
    private val publishLikeUseCase: PublishLikeUseCase
) : KMMViewModel() {
    private val _homeuiModelState = MutableStateFlow(
        HomeUiModel()
    )
    val homeUiModel = _homeuiModelState.asStateFlow()

    init {
        getFeed()
    }

    fun getFeed() {
        viewModelScope.coroutineScope.launch {
            getFeedUseCase()
                .onStart {
                    _homeuiModelState.emit(HomeUiModel(isLoading = true))
                }
                .catch {
                    it.printStackTrace()
                    _homeuiModelState.emit(HomeUiModel(isError = true))

                }.collect { response ->
                    _homeuiModelState.emit(HomeUiModel(postModels = response.postModels))
                }
        }
    }

    fun publishLike(postId: Long, like: Boolean) {
        viewModelScope.coroutineScope.launch {
            publishLikeUseCase(PublishLikeUseCase.PublishLikeParams(postId, like))
                .catch {
                    it.printStackTrace()
                    _homeuiModelState.emit(HomeUiModel(isError = true))
                }.collect { response ->
                    _homeuiModelState.emit(HomeUiModel(postModels = homeUiModel.value.postModels?.map { post ->
                        if (post.id == postId) {
                            post.copy(hasLiked = like)
                        } else {
                            post
                        }
                    }
                    )
                    )
                }
        }
    }
}