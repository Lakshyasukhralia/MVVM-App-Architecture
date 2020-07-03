package com.sukhralia.sampleapparchitectureui.mars.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sukhralia.sampleapparchitectureui.mars.network.MarsApi
import com.sukhralia.sampleapparchitectureui.mars.network.MarsApiService
import com.sukhralia.sampleapparchitectureui.mars.network.MarsProperty
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class OverviewViewModel : ViewModel(){

    private val _response = MutableLiveData<String>()
    private val _sampleProperty = MutableLiveData<MarsProperty>()

    val response : LiveData<String>
        get() = _response

    val sampleProperty : LiveData<MarsProperty>
        get() = _sampleProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties(){

        coroutineScope.launch {
            getResult()
        }

    }

    private suspend fun getResult(){
        var getPropertiesDeferred = MarsApi.retrofitService.getProperties()

        try {
            var listResult = getPropertiesDeferred.await()
            _response.value = "Properties retrieved = ${listResult.size}"
            _sampleProperty.value = listResult[0]
        }catch (t : Throwable){
            _response.value = "Failure = ${t.message}"
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}