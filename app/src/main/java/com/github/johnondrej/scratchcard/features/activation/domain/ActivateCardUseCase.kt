package com.github.johnondrej.scratchcard.features.activation.domain

import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ScratchCardRepository
import com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain.ScratchCardActivationResult
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface ActivateCardUseCase {

    operator fun invoke(code: Int)
}

@OptIn(DelicateCoroutinesApi::class)
class ActivateCardUseCaseImpl(
    private val scratchCardRepository: ScratchCardRepository,
    // TODO error notifier
) : ActivateCardUseCase {

    override fun invoke(code: Int) {
        GlobalScope.launch {
            val result = try {
                scratchCardRepository.activateCard(code)
            } catch (e: Exception) {
                ScratchCardActivationResult.Error
            }

            if (result is ScratchCardActivationResult.Error) {
                // TODO show notification
            }
        }
    }
}
