package com.example.wellcome.data

import androidx.lifecycle.ViewModel
import com.example.services.FavoriteRequest
import com.example.wellcome.repository.Executor
import com.example.wellcome.repository.Result
import com.example.wellcome.repository.TripRepository
import com.example.wellcome.repository.TripResponseParser

class HostRequestViewModel : ViewModel() {
    private val tripRepository = TripRepository(Executor(), TripResponseParser())

    fun sendHostReservation(email:String, hostUuid:String) : Boolean{
        tripRepository.sendHostReservation(email, hostUuid) { result ->
            when(result){
                is Result.Success<Boolean> -> {
                    true
                }
            }
        }
        return false
    }
}