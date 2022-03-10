package com.example.wellcome.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.AccountDto
import com.example.services.HostReservationPresenterDto
import com.example.wellcome.repository.*

class UserViewModel:ViewModel() {
    private val accountRepository = AccountRepository(Executor(), AccountResponseParser())
    private val tripRepository = TripRepository(Executor(), TripResponseParser())

    val uuid = MutableLiveData("")
    val email = MutableLiveData("")
    val phone = MutableLiveData("")
    val birthDate = MutableLiveData<String>("")
    val firstName = MutableLiveData<String>("")
    val lastName = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val gender = MutableLiveData<String>(null)
    val isLogIn = MutableLiveData(false)
    val isLoading = MutableLiveData(false)
    val reservations = MutableLiveData(ArrayList<HostReservationPresenterDto>())

    fun update(){
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
}