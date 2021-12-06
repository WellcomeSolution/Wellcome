package com.example.wellcome.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.wellcome.com.example.wellcome.data.Event
import com.example.wellcome.utils.City
import java.time.LocalDate

class SharedTripViewModel : ViewModel() {
    companion object{
        var cities : Collection<City> = ArrayList()
    }

    val adults = MutableLiveData(0)
    val childs = MutableLiveData(0)
    val pets = MutableLiveData(0)
    val babies = MutableLiveData(0)
    val travelers = MutableLiveData(0)
    val travelersFormat = MutableLiveData(
        "${adults.value} Adults, ${childs.value} Childrens, ${babies.value} Babies")

    val startDate = MutableLiveData<LocalDate>(null)
    val endDate = MutableLiveData<LocalDate>(null)

    val country = MutableLiveData(String())
    val city = MutableLiveData(String())
    val postalCode = MutableLiveData(String())

    fun onAddAdults(){
        adults.value = (adults.value ?: 0) + 1
        travelers.value = (travelers.value ?: 0) + 1
    }

    fun onAddChilds(){
        childs.value = (childs.value ?: 0) + 1
        travelers.value = (travelers.value ?: 0) + 1
    }

    fun onAddPets(){
        pets.value = (pets.value ?: 0) + 1
    }

    fun onAddBabies(){
        babies.value = (babies.value ?: 0) + 1
        travelers.value = (travelers.value ?: 0) + 1
    }

    fun onRemoveAdults(){
        adults.value = (adults.value ?: 0) - 1
        travelers.value = (travelers.value ?: 0) - 1
    }

    fun onRemoveChilds(){
        childs.value = (childs.value ?: 0) - 1
        travelers.value = (travelers.value ?: 0) - 1
    }

    fun onRemovePets(){
        pets.value = (pets.value ?: 0) - 1
    }

    fun onRemoveBabies(){
        babies.value = (babies.value ?: 0) - 1
        travelers.value = (travelers.value ?: 0) - 1
    }
}