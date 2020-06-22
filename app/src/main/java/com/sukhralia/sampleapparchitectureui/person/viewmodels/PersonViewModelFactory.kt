package com.sukhralia.sampleapparchitectureui.person.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sukhralia.sampleapparchitectureui.person.database.PersonDatabaseDao

class PersonViewModelFactory(private val database : PersonDatabaseDao,private val application: Application) : ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            return PersonViewModel(
                database, application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}