package com.github.johnondrej.scratchcard.features.activation.di

import com.github.johnondrej.scratchcard.features.activation.domain.ActivateCardUseCase
import com.github.johnondrej.scratchcard.features.activation.domain.ActivateCardUseCaseImpl
import com.github.johnondrej.scratchcard.features.activation.presentation.ActivationScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val activationModule = module {

    factory<ActivateCardUseCase> {
        ActivateCardUseCaseImpl(
            scratchCardRepository = get(),
            notificationController = get()
        )
    }

    viewModelOf(::ActivationScreenViewModel)
}
