package com.example.pushalarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        createNotificationChannel()

        val type = message.data["type"]?.let {
            NotificationType.valueOf(it)
        }
        val title = message.data["title"]
        val text = message.data["message"]

        type ?: return

        NotificationManagerCompat.from(this)
            .notify(type.id, createNotification(type, title, text))

    }

    private fun createNotification(
        type: NotificationType?,
        title: String?,
        text: String?
    ): Notification {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic__90107_alarm_alert_bell_christmas_notification_icon)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        when (type) {
            NotificationType.NORMAL -> Unit
            NotificationType.EXPANDABLE -> {
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(
                            "😀 😃 😄 😁 😆 😅 😂 🤣 😇 😉 😊 🙂 🙃" +
                                    " ☺ 😋 😌 😍 🥰 😘 😗 😙 😚 🥲 🤪 😜" +
                                    " 😝 😛 🤑 😎 🤓 🥸 🧐 🤠 🥳 🤗 🤡 😏 " +
                                    "😶 😐 😑 😒 🙄 🤨 🤔 🤫 🤭 🤥 😳 😞 " +
                                    "😟 😠 😡 🤬 😔 😕 🙁 ☹ 😬 🥺 😣 😖 😫" +
                                    " 😩 🥱 😤 😮‍💨 😮 😱 😨 😰 😯 😦 😧 😢 " +
                                    "😥 😪 🤤 😓 😭 🤩 😵 😵‍💫 🥴 😲 🤯 🤐 " +
                                    "😷 🤕 🤒"
                        )
                )
            }
            NotificationType.CUSTOM -> {
                notificationBuilder
                    .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(
                        RemoteViews(
                            packageName,
                            R.layout.view_custom_notification
                        ).apply {
                            setTextViewText(R.id.tv_custom_notification_title, title)
                            setTextViewText(R.id.tv_custom_notification_message, text)
                        }
                    )
            }
            null -> TODO()
        }
        return notificationBuilder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_DESCRIPTION

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_NAME = "TEST"
        const val CHANNEL_DESCRIPTION = "test 채널"
        const val CHANNEL_ID = "channel_id"
    }
}