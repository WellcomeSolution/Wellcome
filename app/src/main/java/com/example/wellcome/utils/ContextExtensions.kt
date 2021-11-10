package com.example.wellcome.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity




    fun Context.call(phone:Number){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this as Activity, arrayOf(android.Manifest.permission.CALL_PHONE),
                42)
            Toast.makeText(this, "Please grant My App permission to call.", Toast.LENGTH_SHORT).show()
        } else {
            startCallActivity(phone)
        }
    }

    private fun Context.startCallActivity(phone: Number){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
        startActivity(intent)
    }

    fun Context.searchAddress(address:String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/?q=$address"))
        startActivity(this, browserIntent, null)
    }


