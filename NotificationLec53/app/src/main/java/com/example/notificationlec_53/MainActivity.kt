package com.example.notificationlec_53

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private val CHANNAL_ID = "myNotification"
    private val CHANNAL_NAME = "myNotification"
    private val CHANNAL_DESC = "notification description"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().token.addOnCompleteListener{
            if (it.isSuccessful){
                var token = it.result
                Toast.makeText(this, "$token", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btn_notification).setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {


        // Create an explicit intent for an Activity in your app
        // Create an explicit intent for an Activity in your app
        val intent = Intent(this,MainActivity::class.java).apply {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


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


        var builder = NotificationCompat.Builder(this,CHANNAL_ID)
            .setContentTitle("Tops Technologies")
            .setContentText("Welcome to tops technologies...")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("djijdijavnjnvjnijlfkljijncild jfioajfiodjfoj jiajfiojioefjioe dijavnjnvjnijlfkljijncild jfioajfiodjfoj jiajfioj"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(100,200,300,400,500,400,300,200,400))

        var notificationCompat = NotificationManagerCompat.from(this)
        notificationCompat.notify(1,builder.build())


    }


}