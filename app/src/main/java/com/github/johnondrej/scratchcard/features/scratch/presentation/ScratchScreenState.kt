package com.github.johnondrej.scratchcard.features.scratch.presentation

import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard

data class ScratchScreenState(
    val scratchCard: ScratchCard? = null,
    val isLoading: Boolean = false,
) {

    val canScratch: Boolean
        get() = scratchCard is ScratchCard.New
}
