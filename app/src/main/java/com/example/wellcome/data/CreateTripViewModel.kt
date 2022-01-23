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

    val isLoading = MutableLiveData(false)
    val isImageLoaded = MutableLiveData(false)
    val street = MutableLiveData<String>(null)
    val city = MutableLiveData("")
    val state = MutableLiveData("")
    val postalCode = MutableLiveData("")
    val country = MutableLiveData("")
    val title = MutableLiveData("")
    val description = MutableLiveData("")
    val pictureReceipt = MutableLiveData("")
    val adults = MutableLiveData(0)
    val childs = MutableLiveData(0)
    val babies = MutableLiveData(0)
    val pets = MutableLiveData(0)
    val beds = MutableLiveData(0)
    val bathrooms = MutableLiveData(0)
    val rooms = MutableLiveData(0)

    fun onAddBeds(){
        beds.value = (beds.value ?: 0) + 1
    }

    fun onAddRooms(){
        rooms.value = (rooms.value ?: 0) + 1
    }

    fun onAddBathrooms(){
        bathrooms.value = (bathrooms.value ?: 0) + 1
    }

    fun onRemoveBeds(){
        beds.value = (beds.value ?: 0) - 1
    }

    fun onRemoveRooms(){
        rooms.value = (rooms.value ?: 0) - 1
    }

    fun onRemoveBathrooms(){
        bathrooms.value = (bathrooms.value ?: 0) - 1
    }

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
        isLoading.postValue(true)
        tripRepository.createHost(request) { result ->
            when(result){
                is Result.Success<HostPresenter> -> {
                    isLoading.postValue(false)
                }
            }
        }
    }
}