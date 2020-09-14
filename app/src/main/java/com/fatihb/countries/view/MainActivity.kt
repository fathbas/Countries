package com.fatihb.countries.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.fatihb.countries.R

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager : NotificationManager
    private lateinit var notificationChannel : NotificationChannel
    private lateinit var builder : NotificationCompat.Builder
    private val channelId = "com.fatihb.countries.view"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStop() {
        super.onStop()

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this,ListOfCountry::class.java)

        /*val pendingIntent = PendingIntent.getActivity(this,
            0,intent,PendingIntent.FLAG_UPDATE_CURRENT)*/

        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)

            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

         /*   builder = NotificationCompat.Builder(this,channelId)
                .setContentTitle("Fatih")
                .setContentText("başardım")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,
                    R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)*/

            builder = NotificationCompat.Builder(this,channelId).apply {
                setContentTitle("Fatih")
                setContentText("başardım")
                setSmallIcon(R.drawable.ic_launcher_background)
                setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.ic_launcher_foreground))
                setContentIntent(pendingIntent)
                setAutoCancel(true)
            }
            with(NotificationManagerCompat.from(this)){
                notify(1234,builder.build())
            }

        }
    }
}