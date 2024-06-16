package publish.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.social.domain.publish.usecase.PublishPostUseCase
import app.mistercooper.ui_sharedui_publish_shared.model.PublishUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PublishViewModel (
    private val publishPostUseCase: PublishPostUseCase,
    ) : ViewModel() {
    private val _publishUiModelState = MutableStateFlow(
        PublishUiModel()
    )
    val publishUiModelState = _publishUiModelState.asStateFlow()

    fun publishPost(text: String, file: ByteArray, userName: String){
            viewModelScope.launch {
                publishPostUseCase(PublishPostUseCase.PublishPostParams(text, file, userName))
                    .onStart {
                        _publishUiModelState.emit(PublishUiModel(loading = true))
                    }
                    .catch {
                        it.printStackTrace()
                        _publishUiModelState.emit(PublishUiModel(error = true))
                    }.collect { response ->
                        _publishUiModelState.emit(PublishUiModel(postPublishedSuccess = true))
                    }
            }
        }
}