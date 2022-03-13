package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.databinding.FragmentAddHostConfigurationBinding
import com.example.wellcome.utils.PopError
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_host_address.*
import kotlinx.android.synthetic.main.fragment_add_host_configuration.*

class AddHostConfigurationFragment : Fragment() {
    private val viewModel: CreateTripViewModel by activityViewModels()
    private val popError: PopError = PopError()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        next_button_configuration.setOnClickListener{
            when{
                viewModel.beds.value?.let { it1 -> popError.intTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_configuration,"Please enter your beds number!", Snackbar.LENGTH_LONG)
                        .show()
                }
                viewModel.rooms.value?.let { it1 -> popError.intTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_configuration,"Please enter your rooms number!", Snackbar.LENGTH_LONG)
                        .show()
                }
                viewModel.bathrooms.value?.let { it1 -> popError.intTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_configuration,"Please enter your bathrooms number!", Snackbar.LENGTH_LONG)
                        .show()
                }
                else->{
                    val directions = AddHostConfigurationFragmentDirections.navigateToAddDescriptions()
                    nav.navigate(directions)
                }
            }

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