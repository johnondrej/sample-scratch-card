package com.github.johnondrej.scratchcard

import android.app.Application
import com.github.johnondrej.scratchcard.features.activation.di.activationModule
import com.github.johnondrej.scratchcard.features.scratch.di.scratchModule
import com.github.johnondrej.scratchcard.features.status.di.statusModule
import com.github.johnondrej.scratchcard.shared.core.networking.di.networkingModule
import com.github.johnondrej.scratchcard.shared.scratchcard.di.scratchCardModule
import org.koin.core.context.startKoin

class ScratchCardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                networkingModule,
                scratchCardModule,
                statusModule,
                scratchModule,
                activationModule
            )
        }
    }
}
