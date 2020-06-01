package com.sukhralia.sampleapparchitectureui

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sukhralia.sampleapparchitectureui.database.Person
import com.sukhralia.sampleapparchitectureui.database.PersonDatabase
import com.sukhralia.sampleapparchitectureui.database.PersonDatabaseDao
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PersonDatabaseTest {

    private lateinit var personDao : PersonDatabaseDao
    private lateinit var db : PersonDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context,PersonDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        personDao = db.personDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertAndGetPerson(){
        val person = Person(name = "Ashu", age = 22)
        personDao.insert(person)
        val recentPerson = personDao.getRecentPerson()
        assertEquals(recentPerson?.age,22)
    }

}