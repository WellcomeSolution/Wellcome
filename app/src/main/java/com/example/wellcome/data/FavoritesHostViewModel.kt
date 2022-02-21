package com.example.wellcome.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.FavoriteRequest
import com.example.services.HostPresenter
import com.example.wellcome.repository.Executor
import com.example.wellcome.repository.Result
import com.example.wellcome.repository.TripRepository
import com.example.wellcome.repository.TripResponseParser

class FavoritesHostViewModel : ViewModel(){
    private val tripRepository = TripRepository(Executor(), TripResponseParser())

    val favoriteHostPresenters = MutableLiveData(ArrayList<HostPresenter>())
    val isLoading = MutableLiveData(false)

    fun loadFavoritesHost(email: String) {
        isLoading.postValue(true)
        tripRepository.getFavorites(email) { result ->
            when(result){
                is Result.Success<ArrayList<HostPresenter>> -> {
                    favoriteHostPresenters.postValue(result.data)
                    isLoading.postValue(false)
                }
            }
        }
    }
}