package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_services.*
import kotlinx.android.synthetic.main.services_top_bar.*
import kotlinx.android.synthetic.main.top_app_bar.*

class ServicesFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var tab1 = topbar_layout.getTabAt(0)
        var tab2 = topbar_layout.getTabAt(1)
        tab1?.view?.setOnClickListener{
            launchTripFragment()
        }
        tab2?.view?.setOnClickListener{
            Toast.makeText(context,"test123",Toast.LENGTH_SHORT).show()
            launchLessonFragment()
        }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_services, container, false)

    }
    private fun launchTripFragment(){
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.fragmentTrip)
    }
    private fun launchLessonFragment(){
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.fragmentLesson)
    }

}