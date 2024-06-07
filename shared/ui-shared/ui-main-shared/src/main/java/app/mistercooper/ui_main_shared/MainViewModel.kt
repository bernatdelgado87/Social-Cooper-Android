package app.mistercooper.ui_main_shared

import app.mistercooper.domain_shared_common.user.usecase.IsUserRegisteredUseCase
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(
    private val isUserRegisteredUseCase: IsUserRegisteredUseCase
) : KMMViewModel() {
    private val _isRegisteredState: MutableStateFlow<Boolean?> = MutableStateFlow(
        null
    )
    val isRgisteredState = _isRegisteredState.asStateFlow()

    init {
        isRegistered()
    }

    fun isRegistered() {
        viewModelScope.coroutineScope.launch {
            isUserRegisteredUseCase()
                .catch {
                    it.printStackTrace()
                }.collect { response ->
                    _isRegisteredState.emit(response)
                }
        }
    }

}