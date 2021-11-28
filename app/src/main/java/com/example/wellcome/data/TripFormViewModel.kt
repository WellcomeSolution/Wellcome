package com.example.wellcome.com.example.wellcome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wellcome.models.HostRestrictions
import com.example.wellcome.models.SlotDate
import com.example.wellcome.models.TripCity

class TripFormViewModel : ViewModel() {
    private val _slotDate = MutableLiveData<SlotDate>()
    private val _hostRestrictions = MutableLiveData<HostRestrictions>()
    private val _tripCity = MutableLiveData<TripCity>()

    val slotDate: LiveData<SlotDate> = _slotDate
    val hostRestrictions: LiveData<HostRestrictions> = _hostRestrictions
    val tripCity: LiveData<TripCity> = _tripCity
}