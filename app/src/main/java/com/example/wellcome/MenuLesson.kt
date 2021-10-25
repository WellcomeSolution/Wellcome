package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*


class MenuLesson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.wellcome.R.layout.activity_menu)
        setSupportActionBar(findViewById(com.example.wellcome.R.id.my_toolbar))
        launchSearchFragment()

        bottom_navigation.setOnItemSelectedListener  { item ->
            when(item.itemId) {
                com.example.wellcome.R.id.search_services -> {
                    launchSearchFragment()
                    true
                }
                com.example.wellcome.R.id.add_services -> {
                    launchAddServicesFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun launchAddServicesFragment(){
        supportFragmentManager.
        beginTransaction().replace(com.example.wellcome.R.id.content,AddLessonFragment()).commit()
    }
    private fun launchSearchFragment(){
        supportFragmentManager.
        beginTransaction().replace(com.example.wellcome.R.id.content, SearchLessonFragment()).commit()
    }
}