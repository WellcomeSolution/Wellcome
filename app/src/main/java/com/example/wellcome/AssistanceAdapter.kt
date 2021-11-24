package com.example.wellcome
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.wellcome.utils.DistanceCalculator
import com.example.wellcome.utils.db.AppDatabase
import com.example.wellcome.utils.db.Assistance
import com.example.wellcome.utils.searchAddress
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.assistance_card_view.view.*
import java.util.*

class AssistanceAdapter(val context: Context,private val dataSet: List<Assistance>):
    RecyclerView.Adapter<AssistanceAdapter.ViewHolder>()
{
    var latitude:Double = 48.9891
    var longitude:Double = 2.2585

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.title_assistance
        val address: TextView = itemView.address
        val callButton: Button = itemView.call_button_assistance
        val assistanceButton: Button = itemView.consulter_button_assistance
        val addfavoritesButton:Button = itemView.add_favorites_assitance
        val checkDistanceButton:Button = itemView.check_distances_assistance
        val chipGroup: ChipGroup = itemView.chipGroup
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssistanceAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.assistance_card_view, parent, false)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        dataSet[position].tags.forEach {
            val chip = Chip(context)
            chip.text = it
            viewHolder.chipGroup.addView(chip)
        }

        viewHolder.title.text = dataSet[position].title
        val city = dataSet[position].address.country?.administrativeArea?.locality?.addressLine
        val country = dataSet[position].address.country?.addressLine
        viewHolder.address.text = "$city " +
                "in $country"

        val phone  = dataSet[position].phone

        viewHolder.callButton.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$phone")
            context.startActivity(intent)
        }

        viewHolder.assistanceButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("id",dataSet[position].id)
            bundle.putString("address",dataSet[position].address.country?.administrativeArea?.locality?.thoroughfare?.addressLine)
            val intent = Intent(context, ActivityConsultAssistance::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        viewHolder.addfavoritesButton.setOnClickListener {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "wellcome"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

            db.assistanceDao().update(true,dataSet[position].id)
        }

        viewHolder.checkDistanceButton.setOnClickListener{
            //getLastKnownLocation()
            val geo = Geocoder(context, Locale.getDefault())
            val res = geo.getFromLocationName("Paris",1)
            val distance = DistanceCalculator.Companion.getDistance(latitude, longitude, 48.8924,2.2153)
            Toast.makeText(context, "The distance is ${distance.toInt()} km", Toast.LENGTH_LONG).show()
        }
    }

    // declare a global variable of FusedLocationProviderClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient

// in onCreate() initialize FusedLocationProviderClient


    /**
     * call this method for receive location
     * get location and give callback when successfully retrieve
     * function itself check location permission before access related methods
     *
     */
    fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                (context as Activity?)!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101)

            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                }
            }
    }

    override fun getItemCount() = dataSet.size
}