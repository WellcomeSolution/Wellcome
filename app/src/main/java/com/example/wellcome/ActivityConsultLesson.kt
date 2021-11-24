package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import java.util.*
import com.example.wellcome.utils.db.AppDatabase
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_consult_lesson.*


class ActivityConsultLesson: AppCompatActivity(), OnMapReadyCallback {

    var lat = 48.83592
    var lng = 2.32628
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult_lesson)
        val bundle = intent.extras
        val address = bundle?.getString("address")
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        recycler_view_page.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=ConsultLessonAdapter(context, db.lessonDao().findLessonById(bundle?.getInt("id").toString()))
        }
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_lesson) as SupportMapFragment
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