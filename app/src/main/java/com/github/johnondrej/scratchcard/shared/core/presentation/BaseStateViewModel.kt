package com.github.johnondrej.scratchcard.shared.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Base [ViewModel] containing UI state stream.
 */
abstract class BaseStateViewModel<ScreenState>(initialState: ScreenState) : ViewModel() {

    private val _uiStateStream = MutableStateFlow(initialState)
    val uiStateStream: StateFlow<ScreenState> get() = _uiStateStream

    protected val uiState: ScreenState
        // value is always present since it is initialized as initialState in constructor
        get() = _uiStateStream.value!!

    protected fun updateState(updater: (ScreenState) -> ScreenState) {
        _uiStateStream.value = updater(uiState)
    }
}
