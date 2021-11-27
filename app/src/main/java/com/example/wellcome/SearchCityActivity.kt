package com.example.wellcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_city.*
import android.text.Editable
import com.example.wellcome.utils.CitiesHelper


class SearchCityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_city)
        val cityAdapter = CityAdapter(CitiesHelper.getCities(applicationContext))
        recyclerView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= cityAdapter
        }

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                cityAdapter.filter.filter(cs)
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {}
        })

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.clear -> {
                    searchBar.text.clear()
                    true
                }
                else -> false
            }
        }
    }
}