package com.sukhralia.sampleapparchitectureui.viewmodels

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MyViewModel(startScore : Int) : ViewModel(){

    companion object{
        private const val START = 0L
        private const val INTERVAL = 1000L
        private const val END = 10000L
    }

    private var timer : CountDownTimer

    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private var _currentTime = MutableLiveData<String>()
    val currentTime : LiveData<String>
        get() = _currentTime

    val scoreString = Transformations.map(score,{newScore ->
        newScore.toString()
    })

    init {
        Log.i("myTag","View model created!")
        _score.value = startScore

        timer = object : CountDownTimer(
            END,
            INTERVAL
        ){
            override fun onFinish() {
                _currentTime.value = "Game Over"
            }

            override fun onTick(p0: Long) {
                _currentTime.value = DateUtils.formatElapsedTime(p0/ INTERVAL)
            }
        }

        timer.start()

    }

    fun addScore(){
        _score.value = (score.value)?.plus(1)
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}
