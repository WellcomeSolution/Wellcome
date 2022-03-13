package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.databinding.FragmentAddHostAddressBinding
import com.example.wellcome.utils.PopError
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_host_address.*
import kotlinx.android.synthetic.main.fragment_add_host_address.prev_button_address

class AddHostAddressFragment : Fragment() {
    private val viewModel: CreateTripViewModel by activityViewModels()
    private val popError: PopError = PopError()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        next_button_address.setOnClickListener{
            when {
                viewModel.street.value == null -> {
                    Snackbar.make(prev_button_address,"Please enter your street!", Snackbar.LENGTH_LONG)
                        .show()
                }
                viewModel.city.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_address,"Please enter your city name!", Snackbar.LENGTH_LONG)
                        .show()
                }
                viewModel.state.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_address,"Please enter your state name!", Snackbar.LENGTH_LONG)
                        .show()
                }
                viewModel.postalCode.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_address,"Please enter your postalCode!", Snackbar.LENGTH_LONG)
                        .show()
                }
                viewModel.country.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_address,"Please enter your country!", Snackbar.LENGTH_LONG)
                        .show()
                }

                else -> {
                    val directions = AddHostAddressFragmentDirections.navigateToAddPicture()
                    nav.navigate(directions)
                }
            }

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