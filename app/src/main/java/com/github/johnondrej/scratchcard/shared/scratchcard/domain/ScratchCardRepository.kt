package com.github.johnondrej.scratchcard.shared.scratchcard.domain

import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard
import kotlinx.coroutines.flow.Flow

interface ScratchCardRepository {

    fun observeScratchCard(): Flow<ScratchCard>

    suspend fun scratchCard()

    suspend fun activateCard()
}
