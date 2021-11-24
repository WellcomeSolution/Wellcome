package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.NumberPicker
import android.widget.Toast
import com.example.wellcome.models.Equipments
import com.example.wellcome.models.Priority
import com.example.wellcome.utils.db.*
import kotlinx.android.synthetic.main.fragement_add_trip.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddTripFragment : BaseFragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragement_add_trip, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trip_rooms.setMaxValue(10)
        trip_rooms.setMinValue(0)
        trip_beds.setMaxValue(10)
        trip_beds.setMinValue(0)
        trip_bathrooms.setMaxValue(10)
        trip_bathrooms.setMinValue(0)
        trip_add_button.setOnClickListener{
            db.tripDao().insert(retrieveTrip())
            clearTextEdit()
            Toast.makeText(context,"Assistance added", Toast.LENGTH_SHORT).show()
        }
    }
    private fun clearTextEdit(){
        trip_startdate.text?.clear()
        trip_enddate.text?.clear()
        trip_titre.text?.clear()
        trip_phone.text?.clear()
        trip_address.text?.clear()
        trip_country.text?.clear()
        trip_city.text?.clear()
        trip_travelers.text?.clear()
        trip_postalcode.text?.clear()
        trip_addressline1.text?.clear()
        checkbox1_trip.isChecked = false
        checkbox2_trip.isChecked = false
        checkbox3_trip.isChecked = false
    }
    private fun retrieveTrip(): Trip {
        var fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var startD = LocalDate.parse(trip_startdate.text.toString(),fmt)
        var endD = LocalDate.parse(trip_enddate.text.toString(),fmt)
        var hasDog = false
        var hasBabies = false
        var hasChilds = false
        if(checkbox1_trip.isChecked){
            hasDog = true
        }
        if(checkbox2_trip.isChecked){
            hasBabies = true
        }
        if(checkbox3_trip.isChecked){
            hasChilds = true
        }
        var mlist = mutableListOf<Equipments>()
        if(equip1_trip.isChecked) mlist.add(Equipments.Wifi)
        if(equip2_trip.isChecked) mlist.add(Equipments.HotWater)
        if(equip3_trip.isChecked) mlist.add(Equipments.WashingMachine)
        var list = mlist.toList()
        var ret_trip = Trip(getSlotDate(startD,endD)
            ,getTripCity()
            ,getHostConfiguration(list)
            ,phone = trip_phone.text.toString()
            ,adress = trip_address.text.toString()
            ,travelers = trip_travelers.text.toString().toInt()
            ,hasDogs = hasDog
            ,hasBabies = hasBabies
            ,hasChilds = hasChilds,
            true)
        return ret_trip
    }

    fun getSlotDate(x:LocalDate,y:LocalDate):SlotDate
            = SlotDate(
        startDate = x,
        endDate = y
    )
    fun getTripCity():TripCity
            = TripCity(
        country = trip_country.text.toString(),
        city = trip_city.text.toString(),
        postalCode = trip_postalcode.text.toString(),
        address = trip_addressline1.text.toString()
    )
    fun getHostConfiguration(equipments: List<Equipments>):HostConfiguration
            = HostConfiguration(
        rooms = trip_rooms.value.toInt(),
        beds = trip_beds.value.toInt(),
        bathrooms = trip_bathrooms.value.toInt(),
        equipments = equipments
    )
}