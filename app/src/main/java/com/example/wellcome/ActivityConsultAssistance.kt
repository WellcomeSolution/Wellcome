package com.example.wellcome

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wellcome.utils.db.AppDatabase
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_consult_assistance.*
import kotlinx.android.synthetic.main.material_card_view.*

class ActivityConsultAssistance : AppCompatActivity(), OnMapReadyCallback {

    var lat = 48.83592
    var lng = 2.32628
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult_assistance)
        val bundle = intent.extras
        val address = bundle?.getString("address")
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        recycler_view_c_page_assistance.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=ConsultAssistanceAdapter(context,
                db.assistanceDao().findAssistanceById(bundle?.getInt("id").toString()))
        }
        /*test.setOnClickListener(){
            Toast.makeText(this,address,Toast.LENGTH_SHORT).show()
        }*/
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_assis) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val appointLoc = LatLng(lat, lng)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(appointLoc))
        googleMap.addMarker(
            MarkerOptions().position(appointLoc).title("Marker")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(appointLoc,15f))
    }
}