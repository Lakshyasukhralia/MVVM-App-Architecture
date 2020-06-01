package com.sukhralia.sampleapparchitectureui.utils

import android.os.Handler
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyTimer(lifecycle: Lifecycle) : LifecycleObserver {

    var secondsCount = 0
    private var handler = Handler()
    private lateinit var runnable: Runnable

    init {
        lifecycle.addObserver(this)
    }

    fun startTimer() {
        runnable = Runnable {
            secondsCount++
            Log.i("Timer","Timer is at : $secondsCount")
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }
}