package com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain

sealed interface ScratchCard {

    val code: Int?

    object New : ScratchCard {

        override val code: Int? = null
    }

    data class Scratched(override val code: Int) : ScratchCard

    data class Activated(override val code: Int) : ScratchCard
}
