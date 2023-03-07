package com.github.johnondrej.scratchcard.shared.scratchcard.domain

import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard
import kotlinx.coroutines.flow.Flow

interface ObserveScratchCardUseCase {

    operator fun invoke(): Flow<ScratchCard>
}

class ObserveScratchCardUseCaseImpl(
    private val scratchCardRepository: ScratchCardRepository
) : ObserveScratchCardUseCase {

    override fun invoke(): Flow<ScratchCard> = scratchCardRepository.observeScratchCard()
}
