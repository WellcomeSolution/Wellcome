package com.example.wellcome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.Gender
import com.example.services.HostDetails
import com.example.services.HostPresenter
import com.example.wellcome.repository.*
import java.lang.StringBuilder
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
    val language = MutableLiveData<String>(null)
    val profession = MutableLiveData<String>(null)

    val genderAgeFormat : LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val gender = gender.value ?: return
                val age = age.value ?: return
                val sb = StringBuilder()
                if(gender == Gender.Male) sb.append("M") else sb.append("F")
                sb.append(".$age")
                value = sb.toString()
            }

            addSource(gender) { update() }
            addSource(age) { update() }

            update()
        }

    val nameFormat : LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val firstName = firstName.value ?: return
                val lastName = lastName.value ?: return
                value = "$firstName $lastName"
            }

            addSource(firstName) { update() }
            addSource(lastName) { update() }

            update()
        }

    val addressFormat : LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val city = city.value ?: return
                val postalCode = postalCode.value ?: return
                val country = country.value ?: return
                value = "$city, $postalCode, $country"
            }

            addSource(city) { update() }
            addSource(postalCode) { update() }
            addSource(country) { update() }

            update()
        }

    val hostConfigurationFormat : LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val travelers = travelers.value ?: return
                val rooms = rooms.value ?: return
                val bathrooms = bathrooms.value ?: return
                val sb = StringBuilder()
                sb.append(travelers)
                sb.append(formatTravelers(travelers))
                sb.append(rooms)
                sb.append(formatRooms(rooms))
                sb.append(bathrooms)
                sb.append(formatBathrooms(bathrooms))
                value = sb.toString()
            }

            addSource(travelers) { update() }
            addSource(rooms) { update() }
            addSource(bathrooms) { update() }

            update()
        }

    private fun formatTravelers(travelers: Int) : String
        = if (travelers > 1) " travelers, " else " traveler, "


    private fun formatRooms(rooms: Int) : String
        = if (rooms > 1) " rooms, " else " room, "


    private fun formatBathrooms(bathrooms: Int) : String
        = if (bathrooms > 1) " bathrooms" else " bathroom"

    fun loadHostDetails(id:Int){
        isLoading.value = true
        tripRepository.getHostDetails(id) { result ->
            when(result){
                is Result.Success<HostDetails> -> {
                    title.postValue(result.data.title)
                    city.postValue(result.data.address.city)
                    postalCode.postValue(result.data.address.postalCode)
                    country.postValue(result.data.address.country)
                    language.postValue(result.data.hoster.language)
                    gender.postValue(Gender.valueOf(result.data.hoster.gender))
                    age.postValue(result.data.hoster.age)
                    userDescription.postValue(result.data.hoster.description)
                    travelers.postValue(result.data.travelers)
                    rooms.postValue(result.data.rooms)
                    bathrooms.postValue(result.data.bathrooms)
                    hostDescription.postValue(result.data.description)
                    firstName.postValue(result.data.hoster.firstName)
                    lastName.postValue(result.data.hoster.lastName)
                    isLoading.postValue(false)
                }
            }
        }
    }
}