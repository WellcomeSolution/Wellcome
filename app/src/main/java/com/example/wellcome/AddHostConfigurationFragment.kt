package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.databinding.FragmentAddHostConfigurationBinding
import kotlinx.android.synthetic.main.fragment_add_host_configuration.*
import kotlinx.android.synthetic.main.fragment_add_host_description.*
import kotlinx.android.synthetic.main.fragment_add_host_picture.*

class AddHostConfigurationFragment : Fragment() {
    private val viewModel: CreateTripViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        next_button_configuration.setOnClickListener{
            val directions = AddHostConfigurationFragmentDirections.navigateToAddDescriptions()
            nav.navigate(directions)
        }
        prev_button_configuration.setOnClickListener{
            nav.popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddHostConfigurationBinding.inflate(
            inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}