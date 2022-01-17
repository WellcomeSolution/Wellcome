package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.data.HostViewModel
import com.example.wellcome.databinding.FragmentAddHostAddressBinding
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.fragment_add_host_address.*

class AddHostAddressFragment : Fragment() {
    private val viewModel: CreateTripViewModel by navGraphViewModels(R.id.navigationFragment)
    private val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_button.setOnClickListener{
            val directions = NavigationFragmentDirections.navigateToAddRestrictions()
            nav.navigate(directions)
        }
        prev_button_address.setOnClickListener{
            nav.popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddHostAddressBinding.inflate(
            inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}