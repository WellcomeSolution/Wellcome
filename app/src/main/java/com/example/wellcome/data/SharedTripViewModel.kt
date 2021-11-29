package com.example.wellcome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellcome.MainActivity
import com.example.wellcome.com.example.wellcome.utils.Event
import com.example.wellcome.models.HostRestrictions
import com.example.wellcome.models.SlotDate
import com.example.wellcome.models.TripCity
import com.example.wellcome.utils.CitiesHelper
import com.example.wellcome.utils.City
import kotlinx.coroutines.launch
import java.time.LocalDate

class SharedTripViewModel : ViewModel() {
    companion object {
        val travelersRemoved = Event<Int>()
        val travelersAdded = Event<Int>()

        val childsRemoved = Event<Int>()
        val childsAdded = Event<Int>()

        val petsRemoved = Event<Int>()
        val petsAdded = Event<Int>()

        val babiesRemoved = Event<Int>()
        val babiesAdded = Event<Int>()

        var cities : Collection<City> = ArrayList()
    }

    val travelers = MutableLiveData(0)
    val childs = MutableLiveData(0)
    val pets = MutableLiveData(0)
    val babies = MutableLiveData(0)

    val startDate = MutableLiveData<LocalDate>(null)
    val endDate = MutableLiveData<LocalDate>(null)

    val country = MutableLiveData(String())
    val city = MutableLiveData(String())
    val postalCode = MutableLiveData(String())

    fun onAddTravelers(){
        travelers.value = (travelers.value ?: 0) + 1
        travelersAdded(1)
    }

    fun onAddChilds(){
        childs.value = (childs.value ?: 0) + 1
        childsAdded(1)
    }

    fun onAddPets(){
        pets.value = (pets.value ?: 0) + 1
        petsAdded(1)
    }

    fun onAddBabies(){
        babies.value = (babies.value ?: 0) + 1
        babiesAdded(1)
    }

    fun onRemoveTravelers(){
        travelers.value = (travelers.value ?: 0) - 1
        travelersRemoved(1)
    }

    fun onRemoveChilds(){
        childs.value = (childs.value ?: 0) - 1
        childsRemoved(1)
    }

    fun onRemovePets(){
        pets.value = (pets.value ?: 0) - 1
        petsRemoved(1)
    }

    fun onRemoveBabies(){
        babies.value = (babies.value ?: 0) - 1
        babiesRemoved(1)
    }
}