package com.example.wellcome.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel:ViewModel() {
    val id = MutableLiveData<Int>(1)
    val email = MutableLiveData<String>("jebray@gmail.com")
    val password = MutableLiveData<String>(null)
}