package com.example.wellcome.data

import androidx.lifecycle.*
import com.example.services.HostPresenter
import com.example.wellcome.repository.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SharedLessonViewModel: ViewModel(){
    private val cityRepository = CityRepository(Executor(), CityResponseParser())
    val cities = MutableLiveData(ArrayList<City>())
    val city = MutableLiveData(City())
    val addressFormat: LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val address = city.value?.address ?: return
                if (!address.town.isNullOrEmpty() && !address.country.isNullOrEmpty())
                    value = "${address.town}, ${address.country}"
            }

            addSource(city) { update() }

            update()
        }
    val hostPresenters = MutableLiveData(ArrayList<HostPresenter>())
    val isLoading = MutableLiveData(false)
    val startDate = MutableLiveData<Date>(null)
    val dateFormat: LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val startDate = startDate.value ?: return
                val format = SimpleDateFormat("MMM dd")
                value = "${format.format(startDate)} "

            }
        }
    val lessonType = MutableLiveData<String>(null)
    val lessonTypeFormat : LiveData<String> = MediatorLiveData<String>()
        .apply {
            fun update() {
                val lessonType = lessonType.value ?: return
                value ="${lessonType}"
            }
            addSource(lessonType) { update() }
            update()
        }
}