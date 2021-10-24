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

    fun Context.call(phone:Number){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this as Activity, arrayOf(android.Manifest.permission.CALL_PHONE),
                42)
            Toast.makeText(this, "Please grant My App permission to call.", Toast.LENGTH_SHORT).show()
        } else {
            startCallActivity()
        }
    }

    private fun Context.startCallActivity(){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"))
        startActivity(intent)
    }
