package com.example.wellcome.com.example.wellcome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wellcome.com.example.wellcome.utils.Event

class RestrictionsFormViewModel : ViewModel() {
    companion object{
        val travelersRemoved = Event<Int>()
        val travelersAdded = Event<Int>()

        val childsRemoved = Event<Int>()
        val childsAdded = Event<Int>()

        val petsRemoved = Event<Int>()
        val petsAdded = Event<Int>()

        val babiesRemoved = Event<Int>()
        val babiesAdded = Event<Int>()
    }

    private val _travelers = MutableLiveData(0)
    private val _childs = MutableLiveData(0)
    private val _pets = MutableLiveData(0)
    private val _babies = MutableLiveData(0)

    val travelers: LiveData<Int> = _travelers
    val childs: LiveData<Int> = _childs
    val pets: LiveData<Int> = _pets
    val babies: LiveData<Int> = _babies

    fun onAddTravelers(){
        _travelers.value = (_travelers.value ?: 0) + 1
        travelersAdded(1)
    }

    fun onAddChilds(){
        _childs.value = (_childs.value ?: 0) + 1
        childsAdded(1)
    }

    fun onAddPets(){
        _pets.value = (_pets.value ?: 0) + 1
        petsAdded(1)
    }

    fun onAddBabies(){
        _babies.value = (_babies.value ?: 0) + 1
        babiesAdded(1)
    }

    fun onRemoveTravelers(){
        _travelers.value = (_travelers.value ?: 0) - 1
        travelersRemoved(1)
    }

    fun onRemoveChilds(){
        _childs.value = (_childs.value ?: 0) - 1
        childsRemoved(1)
    }

    fun onRemovePets(){
        _pets.value = (_pets.value ?: 0) - 1
        petsRemoved(1)
    }

    fun onRemoveBabies(){
        _babies.value = (_babies.value ?: 0) - 1
        babiesRemoved(1)
    }
}