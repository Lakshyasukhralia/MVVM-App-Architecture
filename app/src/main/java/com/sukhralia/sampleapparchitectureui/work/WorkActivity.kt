package com.sukhralia.sampleapparchitectureui.work

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.DatabaseUtils
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.sukhralia.sampleapparchitectureui.R
import com.sukhralia.sampleapparchitectureui.databinding.ActivityWorkBinding
import com.sukhralia.sampleapparchitectureui.mars.MarsActivity
import com.sukhralia.sampleapparchitectureui.work.services.ACTION_FOO
import com.sukhralia.sampleapparchitectureui.work.services.MyIntentService
import java.util.*

class WorkActivity : AppCompatActivity() {

    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    private lateinit var myReceiver : BroadcastReceiver
    private lateinit var myAlarmReceiver : BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityWorkBinding>(this,R.layout.activity_work)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.sendNotification.setOnClickListener {
            sendNotification()
        }

        setLocalBroadcast()
        setAlarmBroadcast()

        binding.service.setOnClickListener {
            MyIntentService.startActionFoo(this,"Foo","1")
        }

        binding.alarm.setOnClickListener {
            setAlarm()
        }

    }

    private fun setAlarm(){

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent("myAlarmBroadcast")
        val pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 5000,pendingIntent)
        Toast.makeText(this,Date().toString(),Toast.LENGTH_SHORT).show()
    }

    private fun setLocalBroadcast(){

        val filter = IntentFilter("myBroadcast")
        myReceiver = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                var msg = p1?.getStringExtra("msg")
                Toast.makeText(p0,msg,Toast.LENGTH_SHORT).show()
            }
        }

        registerReceiver(myReceiver,filter)
    }

    private fun setAlarmBroadcast(){

        val filter = IntentFilter("myAlarmBroadcast")
        myAlarmReceiver = object : BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                Toast.makeText(p0,Date().toString(),Toast.LENGTH_SHORT).show()
            }
        }

        registerReceiver(myAlarmReceiver,filter)
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

    override fun onDestroy() {
        unregisterReceiver(myReceiver)
        unregisterReceiver(myAlarmReceiver)
        super.onDestroy()
    }


}