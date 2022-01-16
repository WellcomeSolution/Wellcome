package com.example.wellcome.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateTripViewModel : ViewModel() {
    val street = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val state = MutableLiveData<String>()
    val postalCode = MutableLiveData<String>()
    val country = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val pictureReceipt = MutableLiveData<String>()
    val adults = MutableLiveData<Int>()
    val childs = MutableLiveData<Int>()
    val babies = MutableLiveData<Int>()
    val pets = MutableLiveData<Int>()

    fun onAddAdults(){
        adults.value = (adults.value ?: 0) + 1
    }

    fun onAddChilds(){
        childs.value = (childs.value ?: 0) + 1
    }

    fun onAddPets(){
        pets.value = (pets.value ?: 0) + 1
    }

    fun onAddBabies(){
        babies.value = (babies.value ?: 0) + 1
    }

    fun onRemoveAdults(){
        adults.value = (adults.value ?: 0) - 1
    }

    fun onRemoveChilds(){
        childs.value = (childs.value ?: 0) - 1
    }

    fun onRemovePets(){
        pets.value = (pets.value ?: 0) - 1
    }

    fun onRemoveBabies(){
        babies.value = (babies.value ?: 0) - 1
    }
}