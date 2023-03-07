package com.github.johnondrej.scratchcard.shared.scratchcard.di

import com.github.johnondrej.scratchcard.shared.scratchcard.data.ScratchCardRepositoryImpl
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCase
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ObserveScratchCardUseCaseImpl
import com.github.johnondrej.scratchcard.shared.scratchcard.domain.ScratchCardRepository
import com.github.johnondrej.scratchcard.shared.scratchcard.presentation.notifications.NotificationController
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val scratchCardModule = module {

    single {
        NotificationController(
            context = androidContext()
        )
    }

    single<ScratchCardRepository> {
        ScratchCardRepositoryImpl(
            apiService = get()
        )
    }

    factory<ObserveScratchCardUseCase> {
        ObserveScratchCardUseCaseImpl(
            scratchCardRepository = get()
        )
    }
}
