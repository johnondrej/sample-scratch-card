package com.github.johnondrej.scratchcard.shared.scratchcard.di

import com.github.johnondrej.scratchcard.shared.scratchcard.data.ScratchCardRepositoryImpl
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCase
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCaseImpl
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ScratchCardRepository
import org.koin.dsl.module

val scratchCardModule = module {

    single<ScratchCardRepository> { ScratchCardRepositoryImpl() }

    factory<ObserveScratchCardUseCase> {
        ObserveScratchCardUseCaseImpl(
            scratchCardRepository = get()
        )
    }
}
