package com.example.wellcome.data

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.AccountDto
import com.example.services.HostReservationPresenterDto
import com.example.services.IncomingTripDto
import com.example.wellcome.repository.*
import com.example.wellcome.utils.PopError
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_manage_account.*
class UserViewModel:ViewModel() {
    private val accountRepository = AccountRepository(Executor(), AccountResponseParser())
    private val tripRepository = TripRepository(Executor(), TripResponseParser())
    private val popError: PopError = PopError()
    val uuid = MutableLiveData("")
    val email = MutableLiveData("sunjia@gmail.com")
    val phone = MutableLiveData("")
    val birthDate = MutableLiveData<String>("")
    val firstName = MutableLiveData<String>("")
    val lastName = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val gender = MutableLiveData<String>(null)
    val isLogIn = MutableLiveData(true)
    val isLoading = MutableLiveData(false)
    val reservations = MutableLiveData(ArrayList<HostReservationPresenterDto>())
    val incomingTrips = MutableLiveData(ArrayList<IncomingTripDto>())

    fun update() {
        isLoading.postValue(true)
        accountRepository.updateAccount(
            AccountDto(
                email.value!!,
                password.value!!,
                firstName.value!!,
                lastName.value!!,
                gender.value!!,
                birthDate.value!!,
                phone.value!!
            )) { result ->
            when(result){
                is Result.Success<String> -> {
                    isLogIn.postValue(true)
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun register(){
        isLoading.postValue(true)
        accountRepository.registerAccount(
            AccountDto(
                email.value!!,
                password.value!!,
                firstName.value!!,
                lastName.value!!,
                birthDate.value!!,
                phone.value!!
            )) { result ->
            when(result){
                is Result.Success<String> -> {
                    isLogIn.postValue(true)
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun logIn(){
        isLoading.postValue(true)
        accountRepository.logIn(
            AccountDto(email.value!!, password.value!!)) { result ->
            when(result){
                is Result.Success<AccountDto> -> {
                    firstName.postValue(result.data.firstName)
                    lastName.postValue(result.data.lastName)
                    birthDate.postValue(result.data.birthDate)
                    phone.postValue(result.data.phone)
                    gender.postValue(result.data.gender)
                    isLogIn.postValue(true)
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun loadReservations(){
        isLoading.postValue(true)
        tripRepository.getReservations(email.value!!) { result ->
            when(result){
                is Result.Success<ArrayList<HostReservationPresenterDto>> -> {
                    reservations.postValue(result.data)
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun declineReservation(uuid:String){
        isLoading.postValue(true)
        tripRepository.deleteReservation(uuid!!) { result ->
            when(result){
                is Result.Success<Boolean> -> {
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun acceptReservation(uuid:String){
        isLoading.postValue(true)
        tripRepository.acceptReservation(uuid!!) { result ->
            when(result){
                is Result.Success<Boolean> -> {
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun getIncomingTrip(email:String){
        isLoading.postValue(true)
        tripRepository.getIncomingTrips(email) { result ->
            when(result){
                is Result.Success<ArrayList<IncomingTripDto>> -> {
                    incomingTrips.postValue(result.data)
                    isLoading.postValue(false)
                }
            }
        }
    }
}