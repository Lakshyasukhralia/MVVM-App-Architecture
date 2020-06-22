package com.sukhralia.sampleapparchitectureui.person.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(private val startScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(
                startScore
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}