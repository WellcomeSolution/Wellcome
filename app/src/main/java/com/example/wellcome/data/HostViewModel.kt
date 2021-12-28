package com.example.wellcome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.Gender
import com.example.services.HostDetails
import com.example.services.HostPresenter
import com.example.wellcome.repository.*
import java.util.*
import kotlin.collections.ArrayList

class HostViewModel :ViewModel() {
    private val tripRepository = TripRepository(Executor(), TripResponseParser())

    val isLoading = MutableLiveData(false)
    val title = MutableLiveData<String>(null)
    val city = MutableLiveData<String>(null)
    val country = MutableLiveData<String>(null)
    val postalCode = MutableLiveData<String>(null)
    val firstName = MutableLiveData<String>(null)
    val lastName = MutableLiveData<String>(null)
    val travelers = MutableLiveData<Int>(null)
    val rooms = MutableLiveData<Int>(null)
    val bathrooms = MutableLiveData<Int>(null)
    val latitude = MutableLiveData<Double>(null)
    val longitude = MutableLiveData<Double>(null)
    val gender = MutableLiveData<Gender>(null)
    val age = MutableLiveData<Int>(null)
    val hostDescription = MutableLiveData<String>(null)
    val userDescription = MutableLiveData<String>(null)
    val languages = MutableLiveData<ArrayList<String>>(null)

    fun loadHostDetails(id:Int){
        isLoading.value = true
        tripRepository.getHostDetails(id) { result ->
            when(result){
                is Result.Success<HostDetails> -> {
                    title.postValue(result.data.title)
                    city.postValue(result.data.address.city)
                    postalCode.postValue(result.data.address.postalCode)
                    country.postValue(result.data.address.country)
                    isLoading.value = false
                }
            }
        }
    }
}