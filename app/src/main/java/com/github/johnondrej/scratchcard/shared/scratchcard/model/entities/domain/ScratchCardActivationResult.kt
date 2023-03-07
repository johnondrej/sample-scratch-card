package com.github.johnondrej.scratchcard.shared.scratchcard.model.entities.domain

sealed interface ScratchCardActivationResult {

    object Success : ScratchCardActivationResult

    object Error : ScratchCardActivationResult
}
