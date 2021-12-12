package com.example.wellcome.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.wellcome.com.example.wellcome.data.Event
import com.example.wellcome.utils.City
import kotlinx.android.synthetic.main.activity_dates_form.*
import utils.DateUtils
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class SharedTripViewModel : ViewModel() {
    companion object{
        var cities : Collection<City> = ArrayList()
    }

    val adults = MutableLiveData(0)
    val childs = MutableLiveData(0)
    val pets = MutableLiveData(0)
    val babies = MutableLiveData(0)
    val travelers = MutableLiveData(0)
    val travelersFormat = MutableLiveData(String())

    val startDate = MutableLiveData<Date>(null)
    val endDate = MutableLiveData<Date>(null)
    val dateFormat : LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val startDate = startDate.value ?: return
                val endDate = endDate.value ?: return
                val format = SimpleDateFormat("MMM dd")
                value ="${format.format(startDate)} - ${format.format(endDate)}"

            }

            addSource(startDate) { update() }
            addSource(endDate) { update() }

            update()
        }

    val country = MutableLiveData(String())
    val city = MutableLiveData(String())
    val postalCode = MutableLiveData(String())

    fun updateDate(dates:List<Date>){
        startDate.value = dates.first()
        endDate.value = dates.last()
    }

    private fun updateTravelersFormat(){
        travelersFormat.value =
            "${adults.value} Adults, ${childs.value} Childrens, ${babies.value} Babies"
    }

    fun onAddAdults(){
        adults.value = (adults.value ?: 0) + 1
        travelers.value = (travelers.value ?: 0) + 1
        updateTravelersFormat()
    }

    fun onAddChilds(){
        childs.value = (childs.value ?: 0) + 1
        travelers.value = (travelers.value ?: 0) + 1
        updateTravelersFormat()
    }

    fun onAddPets(){
        pets.value = (pets.value ?: 0) + 1
    }

    fun onAddBabies(){
        babies.value = (babies.value ?: 0) + 1
        travelers.value = (travelers.value ?: 0) + 1
        updateTravelersFormat()
    }

    fun onRemoveAdults(){
        adults.value = (adults.value ?: 0) - 1
        travelers.value = (travelers.value ?: 0) - 1
        updateTravelersFormat()
    }

    fun onRemoveChilds(){
        childs.value = (childs.value ?: 0) - 1
        travelers.value = (travelers.value ?: 0) - 1
        updateTravelersFormat()
    }

    fun onRemovePets(){
        pets.value = (pets.value ?: 0) - 1
    }

    fun onRemoveBabies(){
        babies.value = (babies.value ?: 0) - 1
        travelers.value = (travelers.value ?: 0) - 1
        updateTravelersFormat()
    }
}