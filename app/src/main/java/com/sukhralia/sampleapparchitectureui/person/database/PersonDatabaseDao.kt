package com.sukhralia.sampleapparchitectureui.person.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDatabaseDao {

    @Insert
    fun insert(person : Person)

    @Update
    fun update(person : Person)

    @Query("SELECT * FROM person_table WHERE pId = :key")
    fun get(key : Long) : Person

    @Delete
    fun delete(list : List<Person>)

    @Query("DELETE FROM person_table")
    fun clear()

    @Query("SELECT * FROM person_table ORDER BY pid DESC")
    fun getAllPerson() : LiveData<List<Person>>

    @Query("SELECT * FROM person_table ORDER BY pid DESC LIMIT 1")
    fun getRecentPerson() : Person?

}