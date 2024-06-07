package app.mistercooper.ui_comment_shared.viewmodel

import app.mistercooper.domain.comment.usecase.GetCommentsUseCase
import app.mistercooper.domain.comment.usecase.PublishCommentUseCase
import app.mistercooper.ui_comment_shared.model.PublishCommentUiModel
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class CommentViewModel(
    private val publishCommentUseCase: PublishCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
) : KMMViewModel() {
    private val _commentUiModelState = MutableStateFlow(PublishCommentUiModel())
    val commentUiModel = _commentUiModelState.asStateFlow()

    fun publishComment(comment: String, postId: Long, commentReferentId: Int? = null) {
        viewModelScope.coroutineScope.launch {
            publishCommentUseCase(
                PublishCommentUseCase.PublishCommentParams(comment, postId, commentReferentId)
            ).onStart {
                _commentUiModelState.emit(
                    commentUiModel.value.copy(
                        isError = false,
                        isLoadingPublish = true
                    )
                )
            }.catch {
                it.printStackTrace()
                _commentUiModelState.emit(
                    commentUiModel.value.copy(
                        isError = true,
                        isLoadingPublish = false
                    )
                )
            }.collect {
                _commentUiModelState.emit(
                    PublishCommentUiModel(commentWrapper = it)
                )
            }
        }
    }

    fun getComments(postId: Long) {
        viewModelScope.coroutineScope.launch {
            getCommentsUseCase(
                GetCommentsUseCase.GetCommentsParams(postId)
            )
                .onStart {
                    _commentUiModelState.emit(
                        commentUiModel.value.copy(
                            isError = false,
                            isLoadingComments = true
                        )
                    )
                }
                .catch {
                    it.printStackTrace()
                    _commentUiModelState.emit(
                        commentUiModel.value.copy(
                            isError = true,
                            isLoadingComments = false
                        )
                    )

                }.collect { response ->
                    _commentUiModelState.emit(PublishCommentUiModel(commentWrapper = response))
                }
        }
    }
}