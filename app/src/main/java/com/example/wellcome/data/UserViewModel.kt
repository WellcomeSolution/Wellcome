package com.example.wellcome.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.services.AccountDto
import com.example.wellcome.repository.*

class UserViewModel:ViewModel() {
    private val accountRepository = AccountRepository(Executor(), AccountResponseParser())

    val email = MutableLiveData("jebray@gmail.com")
    val phone = MutableLiveData("0668319800")
    val birthDate = MutableLiveData<String>(null)
    val firstName = MutableLiveData<String>(null)
    val lastName = MutableLiveData<String>(null)
    val password = MutableLiveData<String>(null)
    val isLogIn = MutableLiveData(true)
    val isLoading = MutableLiveData(false)
    fun register(){
        isLoading.postValue(true)
        accountRepository.registerAccount(
            AccountDto(
                email.value!!,
                firstName.value!!,
                lastName.value!!,
                password.value!!,
                birthDate.value!!,
                phone.value!!
            )) { result ->
            when(result){
                is Result.Success<String> -> {
                    isLoading.postValue(false)
                    isLogIn.postValue(true)
                }
            }
        }
    }

    fun logIn(){
        isLoading.postValue(true)
        accountRepository.logIn(
            AccountDto(
                email.value!!,
                null,
                null,
                password.value!!,
                null,
                null)) { result ->
            when(result){
                is Result.Success<AccountDto> -> {
                    firstName.postValue(result.data.firstName)
                    lastName.postValue(result.data.lastName)
                    birthDate.postValue(result.data.birthDate)
                    phone.postValue(result.data.phone)
                    isLoading.postValue(false)
                    isLogIn.postValue(true)
                }
            }
        }
    }
}