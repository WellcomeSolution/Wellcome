package com.example.wellcome.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.FavoriteRequest
import com.example.services.HostRequest
import com.example.services.HostReservationRequest
import com.example.wellcome.repository.Executor
import com.example.wellcome.repository.Result
import com.example.wellcome.repository.TripRepository
import com.example.wellcome.repository.TripResponseParser

class HostRequestViewModel : ViewModel() {
    private val tripRepository = TripRepository(Executor(), TripResponseParser())

    val message = MutableLiveData<String>(null)

    fun sendHostReservation(request:HostReservationRequest) : Boolean{
        tripRepository.sendHostReservation(request) { result ->
            when(result){
                is Result.Success<Boolean> -> {
                    true
                }
            }
        }
        return false
    }
}