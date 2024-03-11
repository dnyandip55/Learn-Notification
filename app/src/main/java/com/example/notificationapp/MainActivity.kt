package com.example.notificationapp

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID="channelId"
    val CHANNEL_NAME="channelName"
    val NOTIFICATION_ID=0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()


        //pending intent

        val intent=Intent(this,MainActivity::class.java)
        val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE)


        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("My Notification")
            .setContentText("I'm Learning Notification in Android")
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()


        val button=findViewById<Button>(R.id.button)
        val notificationManager=NotificationManagerCompat.from(this)

        button.setOnClickListener{
            notificationManager.notify(NOTIFICATION_ID,notification)
        }


    }

    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT).apply {
                description="this is my notification"
                enableLights(true)

            }
            val manager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        }
    }
}