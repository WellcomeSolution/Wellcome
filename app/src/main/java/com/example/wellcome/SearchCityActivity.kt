package com.example.wellcome

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_city.*
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModelProvider
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.repository.City
import com.example.wellcome.utils.CitiesHelper
import com.example.wellcome.utils.themeColor
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_dates_form.*


class SearchCityActivity : AppCompatActivity() {
    private val viewModel: SharedTripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        initAnimations()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_city)
        recyclerView.setBackgroundResource(R.drawable.solid_background)
        recyclerView.doOnPreDraw { startPostponedEnterTransition() }
        val cityAdapter = CityAdapter(viewModel.cities.value as ArrayList<City>)

        recyclerView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= cityAdapter
        }


        val topAppBar = top_app_bar_search_city.findViewById<MaterialToolbar>(R.id.topAppBar)
        val searchBar = topAppBar.findViewById<EditText>(R.id.searchBar)
        searchBar.visibility = View.VISIBLE
        topAppBar.menu.findItem(R.id.clear).isVisible = true

        topAppBar.setNavigationOnClickListener {
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
        }

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                //cityAdapter.filter.filter(cs)
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

    private fun initAnimations(){
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        postponeEnterTransition()
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
            scrimColor = Color.TRANSPARENT
            //setAllContainerColors(applicationContext.themeColor(R.attr.colorSurface))
        }

        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
            scrimColor = Color.TRANSPARENT
            //setAllContainerColors(applicationContext.themeColor(R.attr.colorSurface))
        }

        findViewById<View>(android.R.id.content).transitionName = "shared_element_container_location"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

    }
}