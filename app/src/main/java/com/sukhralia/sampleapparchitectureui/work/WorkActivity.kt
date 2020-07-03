package com.sukhralia.sampleapparchitectureui.work

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.sukhralia.sampleapparchitectureui.R
import com.sukhralia.sampleapparchitectureui.databinding.ActivityWorkBinding
import com.sukhralia.sampleapparchitectureui.mars.MarsActivity

class WorkActivity : AppCompatActivity() {

    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityWorkBinding>(this,R.layout.activity_work)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.sendNotification.setOnClickListener {
            sendNotification()
        }
    }

    private fun sendNotification(){

        val intent = Intent(this,MarsActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this,
            0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val contentView = RemoteViews(packageName,
            R.layout.activity_mars)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            val bigImage = BitmapFactory.decodeResource(this.resources,
                R.drawable.ic_launcher_background)

            builder = Notification.Builder(this,channelId)
//                .setContent(contentView)
                .setContentTitle("My Test Notification")
                .setContentText("Hey Hi Hello")
                .setSmallIcon(R.drawable.loading_image)
                .setStyle(Notification.BigPictureStyle().bigPicture(bigImage))
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,
                    R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }else{

            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(this.resources,
                    R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234,builder.build())

    }
}