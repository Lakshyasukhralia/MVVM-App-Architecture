package com.sukhralia.sampleapparchitectureui.utils

import android.app.Person

fun getPersonNameFromLiveData(list : List<com.sukhralia.sampleapparchitectureui.database.Person>) : String{

    var finalString = ""

    if(list.isNotEmpty()){
        for(i in list.indices){
            finalString += list[i].name
        }
    }

    return finalString
}