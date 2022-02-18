package com.example.wellcome.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel:ViewModel() {
    val email = MutableLiveData("jebray@gmail.com")
    val phone = MutableLiveData("0668319800")
    val password = MutableLiveData<String>(null)
}