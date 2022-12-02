package com.example.notificationlec_53

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService () {


    private val CHANNAL_ID = "myNotification"
    private val CHANNAL_NAME = "myNotification"
    private val CHANNAL_DESC = "notification description"

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message!=null){
            var title = message.notification!!.title
            var message = message.notification!!.body

            showNotification(title,message)
        }
    }


    private fun showNotification(title: String?, message: String?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = NotificationChannel(
                CHANNAL_ID,
                CHANNAL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = CHANNAL_DESC
                enableVibration(true)
                lightColor = Color.GREEN
                vibrationPattern = longArrayOf(100,200,300,400,500,400,300,200,400)
            }


            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
        // Create an explicit intent for an Activity in your app
        // Create an explicit intent for an Activity in your app

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }


        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        var builder = NotificationCompat.Builder(this,CHANNAL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setStyle(
                NotificationCompat.BigTextStyle()
                .bigText(message)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(100,200,300,400,500,400,300,200,400))

        var notificationCompat = NotificationManagerCompat.from(this)
        notificationCompat.notify(1,builder.build())


    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TOKEN", "onNewToken: $token  ")
    }

}