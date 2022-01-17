package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_navigation.*

class NavigationFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    val nav = Navigation.findNavController(requireActivity(), R.id.nav_services_fragment)
                    val directions = ServicesFragmentDirections.navigateToMenu()
                    nav.navigate(directions)
                }
                R.id.page_2 -> {
                    // Respond to navigation item 2 reselection
                }
                R.id.page_3 -> {
                    val nav = Navigation.findNavController(requireActivity(), R.id.nav_services_fragment)
                    val directions = ServicesFragmentDirections.navigateToMenu()
                    nav.navigate(directions)
                }
            }
            true
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

}