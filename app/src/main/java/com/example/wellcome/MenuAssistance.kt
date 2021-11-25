package com.example.wellcome

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
<<<<<<< HEAD
import android.location.Criteria
import android.location.LocationManager
=======
>>>>>>> fede2ff (Change Myactivitymenu Location)
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_assistance_menu.*
import android.location.LocationManager
import android.location.Criteria
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*



<<<<<<< HEAD
=======

>>>>>>> fede2ff (Change Myactivitymenu Location)
class MenuAssistance : AppCompatActivity()
{
    var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private var fournisseur: kotlin.String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assistance_menu)
        setSupportActionBar(findViewById(R.id.assistance_toolbar))
        launchSearchFragment()

        assistance_bottom_navigation.setOnItemSelectedListener  { item ->
            when(item.itemId) {
                R.id.search_assistance -> {
                    launchSearchFragment()
                    true
                }
                R.id.add_assistance -> {
                    launchAddServicesFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun launchAddServicesFragment(){
        supportFragmentManager.
        beginTransaction().replace(R.id.assistance_content,AddAssistanceFragment()).commit()
    }
    private fun launchSearchFragment(){
        supportFragmentManager.
        beginTransaction().replace(R.id.assistance_content, SearchAssistanceFragment()).commit()
    }

    private fun initialiserLocalisation() {
        if (locationManager == null) {
            locationManager =
                applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
            val criteres = Criteria()

            // la précision  : (ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision)
            criteres.accuracy = Criteria.ACCURACY_FINE

            // l'altitude
            criteres.isAltitudeRequired = true

            // la direction
            criteres.isBearingRequired = true

            // la vitesse
            criteres.isSpeedRequired = true

            // la consommation d'énergie demandée
            criteres.isCostAllowed = true
            //criteres.setPowerRequirement(Criteria.POWER_HIGH);
            criteres.powerRequirement = Criteria.POWER_MEDIUM
            fournisseur = locationManager!!.getBestProvider(criteres, true)
            Log.d("GPS", "fournisseur : $fournisseur")
        }
        if (fournisseur != null) {
            // dernière position connue
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("GPS", "no permissions !")
                return
            }
            val localisation = locationManager!!.getLastKnownLocation(
                fournisseur!!
            )



        }
    }


}

