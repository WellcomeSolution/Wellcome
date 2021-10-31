package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*


class MenuLesson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        launchSearchFragment()

        bottom_navigation.setOnItemSelectedListener  { item ->
            when(item.itemId) {
                R.id.search_services -> {
                    launchSearchFragment()
                    true
                }
                R.id.add_services -> {
                    launchAddServicesFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun launchAddServicesFragment(){
        supportFragmentManager.
        beginTransaction().replace(R.id.content,AddLessonFragment()).commit()
    }
    private fun launchSearchFragment(){
        supportFragmentManager.
        beginTransaction().replace(R.id.content, SearchLessonFragment()).commit()
    }
}