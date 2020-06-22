package com.sukhralia.sampleapparchitectureui.mars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sukhralia.sampleapparchitectureui.R
import com.sukhralia.sampleapparchitectureui.databinding.ActivityMainBinding
import com.sukhralia.sampleapparchitectureui.databinding.ActivityMarsBinding

class MarsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMarsBinding>(this,
            R.layout.activity_mars
        )
    }

}