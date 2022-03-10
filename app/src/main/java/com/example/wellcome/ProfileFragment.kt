package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.create_new_trip

class ProfileFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manage_information.setOnClickListener{
            val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            val directions = NavigationFragmentDirections.navigateToManageAccount()
            nav.navigate(directions)
        }

        create_new_trip.setOnClickListener{
            val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            val directions = NavigationFragmentDirections.navigateToAddAddress()
            nav.navigate(directions)
        }

        view_reservations.setOnClickListener{
            val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            val directions = NavigationFragmentDirections.navigateToViewReservations()
            nav.navigate(directions)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}