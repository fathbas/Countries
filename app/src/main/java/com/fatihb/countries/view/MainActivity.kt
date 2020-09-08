package com.fatihb.countries.view

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.fatihb.countries.R
import com.fatihb.countries.viewModel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : NotificationCompat.Builder
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