package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.example.services.*
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.FragmentAddHostAddressBinding
import com.example.wellcome.databinding.FragmentAddHostDescriptionBinding
import com.example.wellcome.utils.PopError
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_host_address.*
import kotlinx.android.synthetic.main.fragment_add_host_description.*
import kotlinx.android.synthetic.main.fragment_add_host_picture.*

class AddHostDescriptionFragment : Fragment() {
    private val createTripViewModel: CreateTripViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private val popError: PopError = PopError()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        prev_button_description.setOnClickListener{
            nav.popBackStack()
        }
        create_host_button.setOnClickListener{
             createTripViewModel.createHost(HostRequest(
                userViewModel.email.value!!,
                createTripViewModel.title.value!!,
                createTripViewModel.description.value!!,
                createTripViewModel.pictureReceipt.value!!,
                Address(
                    createTripViewModel.city.value!!,
                    createTripViewModel.country.value!!,
                    createTripViewModel.postalCode.value!!
                ),
                HostConfiguration(
                    createTripViewModel.rooms.value!!,
                    createTripViewModel.beds.value!!,
                    createTripViewModel.bathrooms.value!!
                ),
                TravelersConfiguration(
                    createTripViewModel.adults.value!!,
                    createTripViewModel.babies.value!!,
                    createTripViewModel.pets.value!!,
                    createTripViewModel.childs.value!!
                )
            ))
        }
        createTripViewModel.isCreated.observe(viewLifecycleOwner, {

            isCreated : Boolean ->
            when{
                createTripViewModel.title.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_description,"Please enter your post title!", Snackbar.LENGTH_LONG)
                        .show()
                }
                createTripViewModel.description.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(prev_button_description,"Please enter your post description!", Snackbar.LENGTH_LONG)
                        .show()
                }
                else->{
                    if(isCreated){
                        val nav = Navigation.findNavController(requireActivity(), com.example.wellcome.R.id.nav_host_fragment)
                        val directions = AddHostDescriptionFragmentDirections.navigateToNavigationFragment()
                        nav.navigate(directions)
                        requireActivity().viewModelStore.clear()
                    }
                }
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddHostDescriptionBinding.inflate(
            inflater, container, false)
        binding.viewModel = createTripViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}