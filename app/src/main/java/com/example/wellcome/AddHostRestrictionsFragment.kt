package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.example.wellcome.R
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.databinding.FragmentAddHostAddressBinding
import com.example.wellcome.databinding.FragmentAddHostRestrictionsBinding
import com.example.wellcome.utils.PopError
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_host_address.*
import kotlinx.android.synthetic.main.fragment_add_host_restrictions.*

class AddHostRestrictionsFragment : Fragment() {
    private val viewModel: CreateTripViewModel by activityViewModels()
    private val popError: PopError = PopError()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        next_button_restrictions.setOnClickListener{

            when {
                viewModel.adults.value?.let { it1 -> popError.intTypeTest(it1) } == true
                    && viewModel.childs.value?.let { it1 -> popError.intTypeTest(it1) } == true
                    && viewModel.babies.value?.let { it1 -> popError.intTypeTest(it1) } == true
                    && viewModel.pets.value?.let { it1 -> popError.intTypeTest(it1) } == true -> {
                Snackbar.make(prev_button_restrictions,"Please add your travelers!", Snackbar.LENGTH_LONG)
                    .show()
            }
                else->{
                    val directions = AddHostRestrictionsFragmentDirections.navigateToAddConfiguration()
                    nav.navigate(directions)
                }
            }

        }
        prev_button_restrictions.setOnClickListener{
            nav.popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddHostRestrictionsBinding.inflate(
            inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}