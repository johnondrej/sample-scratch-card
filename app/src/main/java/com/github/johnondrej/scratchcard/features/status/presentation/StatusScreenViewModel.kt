package com.github.johnondrej.scratchcard.features.status.presentation

import androidx.lifecycle.viewModelScope
import com.github.johnondrej.scratchcard.shared.core.presentation.BaseStateViewModel
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCase
import kotlinx.coroutines.launch

class StatusScreenViewModel(
    private val observeScratchCard: ObserveScratchCardUseCase
) : BaseStateViewModel<StatusScreenState>(StatusScreenState()) {

    init {
        observeData()
    }

    private fun observeData() {
        viewModelScope.launch {
            observeScratchCard().collect { scratchCard ->
                updateState { state -> state.copy(scratchCard = scratchCard) }
            }
        }
    }
}
