package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.services.TripPattern
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.FragmentTripConfigurationModifyBinding
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.fragment_trip_configuration_modify.*

class ModifyTripConfigurationFragment : Fragment() {
    private val viewModel: SharedTripViewModel by activityViewModels()
    private lateinit var nav : NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav = findNavController()

        editText_restrictions.setOnClickListener{
            val directions = ModifyTripConfigurationFragmentDirections.navigateToRestrictions()
            nav.navigate(directions)
        }

        editText_dates.setOnClickListener{
            val directions = ModifyTripConfigurationFragmentDirections.navigateToDates()
            nav.navigate(directions)
        }

        editText_location.setOnClickListener{
            val directions = ModifyTripConfigurationFragmentDirections.navigateToLocalisation()
            nav.navigate(directions)
        }

        search_button.setOnClickListener{
            HostsFragment.modalBottomSheet.dismiss()
            val pattern = TripPattern(
                viewModel.adults.value!!,
                viewModel.babies.value!!,
                viewModel.childs.value!!,
                viewModel.city.value?.lon!!.toDouble(),
                viewModel.city.value?.lat!!.toDouble()
            )
            viewModel.loadHosts(pattern)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        val binding =  FragmentTripConfigurationModifyBinding
            .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}