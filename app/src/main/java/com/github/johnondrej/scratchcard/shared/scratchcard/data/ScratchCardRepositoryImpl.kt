package com.github.johnondrej.scratchcard.shared.scratchcard.data

import com.github.johnondrej.scratchcard.shared.core.networking.ApiService
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ScratchCardRepository
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCard
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCardActivationResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class ScratchCardRepositoryImpl(
    private val apiService: ApiService
) : ScratchCardRepository {

    private val scratchCardFlow = MutableStateFlow<ScratchCard>(ScratchCard.New)

    override fun observeScratchCard(): Flow<ScratchCard> = scratchCardFlow

    override suspend fun scratchCard() {
        delay(SCRATCH_DELAY)
        scratchCardFlow.value = ScratchCard.Scratched(code = generateCode(), isActivating = false)
    }

    override suspend fun activateCard(code: Int): ScratchCardActivationResult {
        scratchCardFlow.value = ScratchCard.Scratched(code = code, isActivating = true)

        val result = try {
            val response = apiService.getVersion(code)

            if ((response.android?.toIntOrNull() ?: 0) > ACTIVATION_SUCCESS_THRESHOLD) {
                scratchCardFlow.value = ScratchCard.Activated(code)
                ScratchCardActivationResult.Success
            } else {
                ScratchCardActivationResult.Error
            }
        } catch (e: Exception) {
            ScratchCardActivationResult.Error
        }

        if (result == ScratchCardActivationResult.Error) {
            scratchCardFlow.value = ScratchCard.Scratched(code = code, isActivating = false)
        }
        return result
    }

    private fun generateCode() = Random.nextInt(
        from = 0,
        until = Int.MAX_VALUE
    )

    companion object {

        private val SCRATCH_DELAY = 2.seconds
        private val ACTIVATION_SUCCESS_THRESHOLD = 80_000
    }
}
