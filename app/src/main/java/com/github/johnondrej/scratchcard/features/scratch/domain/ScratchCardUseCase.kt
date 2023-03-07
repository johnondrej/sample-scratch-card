package com.github.johnondrej.scratchcard.features.scratch.domain

import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ScratchCardRepository

interface ScratchCardUseCase {

    suspend operator fun invoke()
}

class ScratchCardUseCaseImpl(
    private val scratchCardRepository: ScratchCardRepository
) : ScratchCardUseCase {

    override suspend fun invoke() {
        scratchCardRepository.scratchCard()
    }
}
