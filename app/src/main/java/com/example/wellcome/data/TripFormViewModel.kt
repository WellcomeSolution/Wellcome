package com.example.wellcome.com.example.wellcome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wellcome.models.HostRestrictions
import com.example.wellcome.models.SlotDate
import com.example.wellcome.models.TripCity

class TripFormViewModel : ViewModel() {
    var slotDate = MutableLiveData<SlotDate>()
    var hostRestrictions = MutableLiveData<HostRestrictions>()
    var tripCity = MutableLiveData<TripCity>()

}