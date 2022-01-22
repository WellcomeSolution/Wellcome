package com.example.wellcome.data

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.FileUploadResult
import com.example.services.HostPresenter
import com.example.services.HostRequest
import com.example.services.TripPattern
import com.example.wellcome.repository.Executor
import com.example.wellcome.repository.Result
import com.example.wellcome.repository.TripRepository
import com.example.wellcome.repository.TripResponseParser

class CreateTripViewModel : ViewModel() {
    private val tripRepository = TripRepository(Executor(), TripResponseParser())

    val isImageLoaded = MutableLiveData(false)
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

    fun uploadImage(bitmap:Bitmap){
        tripRepository.uploadImage(bitmap) { result ->
            when(result){
                is Result.Success<FileUploadResult> -> {
                    pictureReceipt.postValue(result.data.fileName)
                    isImageLoaded.postValue(true)
                }
            }
        }
    }

    fun createHost(request:HostRequest){
        tripRepository.createHost(request) { result ->
            when(result){
                is Result.Success<HostPresenter> -> {

                }
            }
        }
    }
}