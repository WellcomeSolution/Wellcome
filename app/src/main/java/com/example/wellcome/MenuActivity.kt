package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.wellcome.R.layout.activity_menu)
        setSupportActionBar(findViewById(com.example.wellcome.R.id.my_toolbar))
        launchHostsFragment()

        bottom_navigation.setOnItemSelectedListener  { item ->
            when(item.itemId) {
                com.example.wellcome.R.id.hosts -> {
                    launchHostsFragment()
                    true
                }
                com.example.wellcome.R.id.search_services -> {
                    launchSearchFragment()
                    true
                }
                com.example.wellcome.R.id.add_hosts -> {
                    launchAddHostsFragment()
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

    private fun launchHostsFragment(){
        supportFragmentManager.
        beginTransaction().replace(com.example.wellcome.R.id.content, HostsFragment()).commit()
    }
    private fun launchAddServicesFragment(){
        supportFragmentManager.
        beginTransaction().replace(com.example.wellcome.R.id.content,AddServicesFragment()).commit()
    }
    private fun launchAddHostsFragment(){
        supportFragmentManager.
        beginTransaction().replace(com.example.wellcome.R.id.content, AddHostsFragment()).commit()
    }
    private fun launchSearchFragment(){
        supportFragmentManager.
        beginTransaction().replace(com.example.wellcome.R.id.content, SearchFragment()).commit()
    }
}