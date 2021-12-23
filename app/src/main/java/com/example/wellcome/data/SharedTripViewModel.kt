package com.example.wellcome.data

import androidx.lifecycle.*
import com.example.services.Host
import com.example.services.TripPattern
import com.example.wellcome.CityResponseParser
import com.example.wellcome.repository.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import kotlin.collections.ArrayList

class SharedTripViewModel: ViewModel() {
    private val cityRepository = CityRepository(Executor(), CityResponseParser())
    private val tripRepository = TripRepository(Executor(), TripResponseParser())

    val cities = MutableLiveData(ArrayList<City>())
    val city = MutableLiveData(City())
    val addressFormat : LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val address = city.value?.address ?: return
                if(!address.city.isNullOrEmpty() && !address.country.isNullOrEmpty())
                    value ="${address.city}, ${address.country}"
            }

            addSource(city) { update() }

            update()
        }
    val hosts = MutableLiveData(ArrayList<Host>())
    val isLoading = MutableLiveData(false)
    val adults = MutableLiveData(0)
    val childs = MutableLiveData(0)
    val pets = MutableLiveData(0)
    val babies = MutableLiveData(0)
    val travelers = MutableLiveData(0)
    val travelersFormat : LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val adults = adults.value ?: return
                val childs = childs.value ?: return
                val babies = babies.value ?: return
                value ="${adults} Adults, ${childs} Childrens, ${babies} Babies"
            }

            addSource(adults) { update() }
            addSource(childs) { update() }
            addSource(babies) { update() }

            update()
        }

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

    fun updateCities(prefix:String){
        cityRepository.getCities(prefix) { result ->
            when(result){
                is Result.Success<ArrayList<City>> -> {
                    cities.postValue(result.data)
                }
            }
        }
    }

    fun loadHosts(pattern:TripPattern){
        isLoading.postValue(true)
        tripRepository.getHosts(pattern) { result ->
            when(result){
                is Result.Success<ArrayList<Host>> -> {
                    hosts.postValue(result.data)
                }
            }
        }
    }

    fun updateDate(dates:List<Date>){
        startDate.value = dates.first()
        endDate.value = dates.last()
    }

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