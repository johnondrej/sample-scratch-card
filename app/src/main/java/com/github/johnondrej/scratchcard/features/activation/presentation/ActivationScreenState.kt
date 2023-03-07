package com.github.johnondrej.scratchcard.features.activation.presentation

import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard

data class ActivationScreenState(
    val scratchCard: ScratchCard? = null,
    val isLoading: Boolean = false,
) {

    val canActivate: Boolean
        get() = scratchCard is ScratchCard.Scratched
}
