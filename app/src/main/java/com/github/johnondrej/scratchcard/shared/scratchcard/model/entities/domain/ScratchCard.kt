package com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain

sealed interface ScratchCard {

    object New : ScratchCard

    data class Scratched(val code: Int) : ScratchCard

    data class Activated(val code: Int) : ScratchCard
}
