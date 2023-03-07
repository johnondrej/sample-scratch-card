package com.github.johnondrej.scratchcard

import android.app.Application
import com.github.johnondrej.scratchcard.features.activation.di.activationModule
import com.github.johnondrej.scratchcard.features.scratch.di.scratchModule
import com.github.johnondrej.scratchcard.features.status.di.statusModule
import com.github.johnondrej.scratchcard.shared.core.networking.di.networkingModule
import com.github.johnondrej.scratchcard.shared.scratchcard.di.scratchCardModule
import com.github.johnondrej.scratchcard.shared.scratchcard.presentation.notifications.NotificationController
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScratchCardApplication : Application() {

    private val notificationController: NotificationController by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ScratchCardApplication)
            modules(
                networkingModule,
                scratchCardModule,
                statusModule,
                scratchModule,
                activationModule
            )
        }

        setupNotificationChannel()
    }

    private fun setupNotificationChannel() {
        notificationController.setupNotificationChannel()
    }
}
