package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
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
        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val directions = NavigationFragmentDirections.navigateToTrips()
        nav.navigate(directions)
    }
    private fun launchLessonFragment(){
        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val directions = NavigationFragmentDirections.navigateToLessons()
        nav.navigate(directions)
    }


}