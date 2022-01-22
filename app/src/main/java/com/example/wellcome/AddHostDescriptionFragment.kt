package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.databinding.FragmentAddHostAddressBinding
import com.example.wellcome.databinding.FragmentAddHostDescriptionBinding
import kotlinx.android.synthetic.main.fragment_add_host_description.*
import kotlinx.android.synthetic.main.fragment_add_host_picture.*
import kotlinx.android.synthetic.main.fragment_add_host_picture.next_button

class AddHostDescriptionFragment : Fragment() {
    private val viewModel: CreateTripViewModel by navGraphViewModels(R.id.navigationFragment)
    private val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_button.setOnClickListener{
            val directions = NavigationFragmentDirections.navigateToAddDescriptions()
            nav.navigate(directions)
        }
        prev_button_description.setOnClickListener{
            nav.popBackStack()
        }
        create_host_button.setOnClickListener{
            view
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddHostDescriptionBinding.inflate(
            inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}