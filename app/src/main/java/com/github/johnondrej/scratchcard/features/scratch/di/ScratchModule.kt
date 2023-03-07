package com.github.johnondrej.scratchcard.features.scratch.di

import com.github.johnondrej.scratchcard.features.scratch.domain.ScratchCardUseCase
import com.github.johnondrej.scratchcard.features.scratch.domain.ScratchCardUseCaseImpl
import com.github.johnondrej.scratchcard.features.scratch.presentation.ScratchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val scratchModule = module {

    viewModelOf(::ScratchScreenViewModel)

    factory<ScratchCardUseCase> {
        ScratchCardUseCaseImpl(
            scratchCardRepository = get()
        )
    }
}
