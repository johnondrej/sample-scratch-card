package com.github.johnondrej.scratchcard.shared.scratchcard.presentation.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.github.johnondrej.scratchcard.R

class NotificationController(
    private val context: Context
) {

    private val notificationManager: NotificationManagerCompat
        get() = NotificationManagerCompat.from(context)

    fun setupNotificationChannel() {
        val channel = NotificationChannelCompat.Builder(
            ACTIVATION_CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_DEFAULT
        ).setName(context.getString(R.string.notification_activation_channel_name))
            .build()

        notificationManager.createNotificationChannel(channel)
    }

    fun showActivationErrorNotification() {
        showNotification(
            iconRes = R.drawable.ic_notification_error,
            title = context.getString(R.string.notification_activation_error_title),
            text = context.getString(R.string.notification_activation_error_text)
        )
    }

    @SuppressLint("MissingPermission") // checked inside notificationPermissionGranted()
    private fun showNotification(
        @DrawableRes iconRes: Int,
        title: String,
        text: String
    ) {
        val notification = NotificationCompat.Builder(context, ACTIVATION_CHANNEL_ID)
            .setSmallIcon(iconRes)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (notificationPermissionGranted()) {
            notificationManager.notify(ACTIVATION_ERROR_NOTIFICATION_ID, notification)
        }
    }

    private fun notificationPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    companion object {

        private const val ACTIVATION_ERROR_NOTIFICATION_ID = 0
        private const val ACTIVATION_CHANNEL_ID = "activation_channel"
    }
}
