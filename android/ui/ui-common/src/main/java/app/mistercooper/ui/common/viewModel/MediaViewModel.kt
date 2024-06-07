package app.mistercooper.ui.common.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.domain.common.feature.media.usecase.GetImageFromUriUseCase
import app.mistercooper.domain.common.feature.media.usecase.GetMediaImagesFromDeviceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File

class MediaViewModel(
    private val getMediaImagesFromDeviceUseCase: GetMediaImagesFromDeviceUseCase,
    private val getImageFromUriUseCase: GetImageFromUriUseCase,
) : ViewModel() {
    private val _errorState = MutableStateFlow(
        false
    )
    val errorState = _errorState.asStateFlow()

    var selectedFile: MutableStateFlow<File?> = MutableStateFlow(null)

    fun getLocalMediaImages() {
        viewModelScope.launch {
            getMediaImagesFromDeviceUseCase()
                .catch {
                    it.printStackTrace()
                    _errorState.emit(true)
                }
        }
    }

    fun onPhotoSelected(photo: Uri?) {
        viewModelScope.launch {
            photo?.let {
                        getImageFromUriUseCase(
                                photo.toString()
                        ).collect { file ->
                            selectedFile.emit(file)
                        }
            } ?: _errorState.emit(true)
        }
    }

}