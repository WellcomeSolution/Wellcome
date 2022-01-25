package com.example.wellcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

import androidx.fragment.app.Fragment
import android.content.Intent
import android.util.Log
import android.view.Window
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.data.HostViewModel
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.utils.CitiesHelper
import com.example.wellcome.utils.City
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_trip.*
import kotlinx.android.synthetic.main.top_app_bar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedTripViewModel: SharedTripViewModel by viewModels()
        val userViewModel: UserViewModel by viewModels()
        val createTripViewModel: CreateTripViewModel by viewModels()
        setContentView(R.layout.activity_main)

    }

}