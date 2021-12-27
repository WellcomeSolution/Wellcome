package com.example.wellcome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.services.Gender
import com.example.wellcome.repository.City
import java.util.*
import kotlin.collections.ArrayList

class HostViewModel {
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
}