package main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.social.domain.main.usecase.IsUserRegisteredUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(
    private val isUserRegisteredUseCase: IsUserRegisteredUseCase
) : ViewModel() {
    private val _isRegisteredState: MutableStateFlow<Boolean?> = MutableStateFlow(
        null
    )
    val isRgisteredState = _isRegisteredState.asStateFlow()

    init {
        isRegistered()
    }

    fun isRegistered() {
        viewModelScope.launch {
            isUserRegisteredUseCase.run()
                .catch {
                    it.printStackTrace()
                }.collect { response ->
                    _isRegisteredState.emit(response)
                }
        }
    }

}