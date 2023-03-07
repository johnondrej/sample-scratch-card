package com.github.johnondrej.scratchcard.features.activation.presentation

import androidx.lifecycle.viewModelScope
import com.github.johnondrej.scratchcard.features.activation.domain.ActivateCardUseCase
import com.github.johnondrej.scratchcard.shared.core.presentation.BaseStateViewModel
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCase
import kotlinx.coroutines.launch

class ActivationScreenViewModel(
    private val activateCard: ActivateCardUseCase,
    private val observeScratchCard: ObserveScratchCardUseCase
) : BaseStateViewModel<ActivationScreenState>(ActivationScreenState()) {

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

    fun activate(code: Int) {
        activateCard(code)
    }
}
