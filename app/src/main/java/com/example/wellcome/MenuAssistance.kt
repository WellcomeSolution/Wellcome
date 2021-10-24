package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_assistance_menu.*


class MenuAssistance : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.wellcome.R.layout.activity_assistance_menu)
        setSupportActionBar(findViewById(com.example.wellcome.R.id.assistance_toolbar))
        launchSearchFragment()

        assistance_bottom_navigation.setOnItemSelectedListener  { item ->
            when(item.itemId) {
                com.example.wellcome.R.id.search_assistance -> {
                    launchSearchFragment()
                    true
                }
                com.example.wellcome.R.id.add_assistance -> {
                    launchAddServicesFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun launchAddServicesFragment(){
        supportFragmentManager.
        beginTransaction().replace(com.example.wellcome.R.id.assistance_content,AddAssistanceFragment()).commit()
    }
    private fun launchSearchFragment(){
        supportFragmentManager.
        beginTransaction().replace(com.example.wellcome.R.id.assistance_content, SearchAssistanceFragment()).commit()
    }
}