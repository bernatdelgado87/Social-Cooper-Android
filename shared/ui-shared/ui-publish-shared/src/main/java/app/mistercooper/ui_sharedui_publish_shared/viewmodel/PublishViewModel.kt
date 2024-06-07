package app.mistercooper.ui_sharedui_publish_shared.viewmodel

import app.mistercooper.social.domain.feature.publish.usecase.PublishPostUseCase
import app.mistercooper.ui_sharedui_publish_shared.model.PublishUiModel
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File

class PublishViewModel (
    private val publishPostUseCase: PublishPostUseCase,
    ) : KMMViewModel() {
    private val _publishUiModelState = MutableStateFlow(
        PublishUiModel()
    )
    val publishUiModelState = _publishUiModelState.asStateFlow()

    fun publishPost(text: String, file: File){
            viewModelScope.coroutineScope.launch {
                publishPostUseCase(PublishPostUseCase.PublishPostParams(text, file))
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