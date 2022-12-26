package com.example.pushalarmservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        createNotificationChannel()

        val type = message.data["type"]?.let { NotificationType.valueOf(it) }
        val title = message.data["title"]
        val text = message.data["message"]

        type?.id ?: return

        NotificationManagerCompat.from(this).notify(
            type.id, createNotification(type, title, text)
        )
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun intentMain(type: NotificationType): PendingIntent? {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notificationType", "${type.title}타입")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        return getActivity(this, type.id, intent, FLAG_MUTABLE)

    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun createNotification(
        type: NotificationType, title: String?, msg: String?
    ): Notification {
        val pendingIntent = intentMain(type)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setAutoCancel(true)
            setSmallIcon(R.drawable.ic_baseline_doorbell_24)
            setContentTitle(title)
            setContentText(msg)
            setContentIntent(pendingIntent)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }

        when (type) {
            NotificationType.EXPANDABLE -> {
                builder.setStyle(
                    NotificationCompat.BigTextStyle().bigText(
                        getString(R.string.notification_expandable)
                    )
                )
            }
            NotificationType.CUSTOM -> {
                builder.apply {
                    setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    setCustomContentView(RemoteViews(
                        packageName, R.layout.notification_custom
                    ).apply {
                        setTextViewText(R.id.title, title)
                        setTextViewText(R.id.message, msg)
                    })
                }
            }
            else -> {
                //일반형
                Unit
            }
        }

        return builder.build()
    }


    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = CHANNEL_DESCRIPTION
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )

    }

    companion object {
        private const val CHANNEL_NAME = "채널 1"
        private const val CHANNEL_DESCRIPTION = "채널 1 입니다."
        private const val CHANNEL_ID = "channel id"
    }
}
