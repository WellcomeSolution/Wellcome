package com.example.wellcome.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.HostReservationDto
import com.example.wellcome.repository.Executor
import com.example.wellcome.repository.Result
import com.example.wellcome.repository.TripRepository
import com.example.wellcome.repository.TripResponseParser

class HostRequestViewModel : ViewModel() {
    private val tripRepository = TripRepository(Executor(), TripResponseParser())

    val message = MutableLiveData<String>(null)
    val requestSent = MutableLiveData(false)

    fun sendHostReservation(dto:HostReservationDto) : Boolean{
        tripRepository.sendHostReservation(dto) { result ->
            when(result){
                is Result.Success<HostReservationDto> -> {
                    true
                }
            }
        }
        return false
    }
}