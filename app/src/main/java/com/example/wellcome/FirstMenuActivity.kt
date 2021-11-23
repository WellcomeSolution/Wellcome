package com.example.wellcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_firstmenu.*

class FirstMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(com.example.wellcome.R.layout.activity_firstmenu)

        lesson_button.setOnClickListener(){
            val intent = Intent(this,MenuLesson::class.java)
            startActivity(intent)
        }

        trip_button.setOnClickListener(){
            val intent = Intent(this,MenuTrip::class.java)
            startActivity(intent)
        }

        assistance_button.setOnClickListener(){
            val intent = Intent(this,MenuAssistance::class.java)
            startActivity(intent)
        }
    }
}