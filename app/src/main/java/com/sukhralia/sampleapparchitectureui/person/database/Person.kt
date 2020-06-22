package com.sukhralia.sampleapparchitectureui.person.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    var pId : Long = 0L,
    @ColumnInfo(name = "name")
    var name : String = "",
    @ColumnInfo(name = "age")
    var age : Int = 0
)