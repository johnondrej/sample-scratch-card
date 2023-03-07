package com.github.johnondrej.scratchcard.shared.scratchcard.data

import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ScratchCardRepository
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class ScratchCardRepositoryImpl : ScratchCardRepository {

    private val scratchCardFlow = MutableStateFlow<ScratchCard>(ScratchCard.New)

    override fun observeScratchCard(): Flow<ScratchCard> = scratchCardFlow

    override suspend fun scratchCard() {
        delay(SCRATCH_DELAY)
        scratchCardFlow.value = ScratchCard.Scratched(generateCode())
    }

    override suspend fun activateCard() {
        TODO("Not yet implemented")
    }

    private fun generateCode() = Random.nextInt()

    companion object {

        private val SCRATCH_DELAY = 2.seconds
    }
}
