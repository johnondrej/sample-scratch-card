package com.github.johnondrej.scratchcard.features.scratch.presentation

import androidx.lifecycle.viewModelScope
import com.github.johnondrej.scratchcard.features.scratch.domain.ScratchCardUseCase
import com.github.johnondrej.scratchcard.shared.core.presentation.BaseStateViewModel
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCase
import kotlinx.coroutines.launch

class ScratchScreenViewModel(
    private val scratchCard: ScratchCardUseCase,
    private val observeScratchCard: ObserveScratchCardUseCase
) : BaseStateViewModel<ScratchScreenState>(ScratchScreenState()) {

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

    fun scratch() {
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = true) }
            scratchCard()
            updateState { state -> state.copy(isLoading = false) }
        }
    }
}
