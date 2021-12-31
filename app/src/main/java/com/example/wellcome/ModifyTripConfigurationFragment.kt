package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.wellcome.databinding.FragmentTripConfigurationModifyBinding
import kotlinx.android.synthetic.main.fragment_trip_configuration_modify.*

class ModifyTripConfigurationFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText_restrictions.setOnClickListener{
            val nav = findNavController()
            val directions = ModifyTripConfigurationFragmentDirections.navigateToRestrictions()
            nav.navigate(directions)
        //nav.navigate(directions)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =  FragmentTripConfigurationModifyBinding
            .inflate(inflater, container, false)
        return binding.root
    }
}