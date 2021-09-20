package com.example.wellcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnadduser: Button = findViewById(R.id.adduser)
        btnadduser.setOnClickListener(){
            var intent = Intent(this,AddUserActivity::class.java)
            startActivity(intent)
        }
    }

}