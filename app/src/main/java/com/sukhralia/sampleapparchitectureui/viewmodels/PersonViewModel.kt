package com.sukhralia.sampleapparchitectureui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sukhralia.sampleapparchitectureui.database.Person
import com.sukhralia.sampleapparchitectureui.database.PersonDatabaseDao
import com.sukhralia.sampleapparchitectureui.utils.getPersonNameFromLiveData
import kotlinx.coroutines.*

class PersonViewModel(val database : PersonDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var personViewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        personViewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main +  personViewModelJob)
    var recentPerson = MutableLiveData<Person?>()
    private val persons = database.getAllPerson()

    val personsString = Transformations.map(persons) { persons ->
        getPersonNameFromLiveData(persons)
    }

    init {
        initializeRecentPerson()
    }

    private fun initializeRecentPerson() {
        uiScope.launch {
            recentPerson.value = getRecentPersonFromDatabase()
        }
    }

    private suspend fun getRecentPersonFromDatabase():  Person? {
        return withContext(Dispatchers.IO) {
            var recentPerson = database.getRecentPerson()
            recentPerson
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun update(person: Person) {
        withContext(Dispatchers.IO) {
            database.update(person)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            recentPerson.value = null
        }
    }

    private suspend fun insert(person: Person) {
        withContext(Dispatchers.IO) {
            database.insert(person)
        }
    }

    fun insertAndRetrieve(name : String, age: Int) {
        uiScope.launch {
            val newPerson = Person(name = name,age = age)
            insert(newPerson)
            recentPerson.value = getRecentPersonFromDatabase()
        }
    }
}