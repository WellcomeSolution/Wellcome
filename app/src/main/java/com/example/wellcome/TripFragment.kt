package com.example.wellcome

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wellcome.com.example.wellcome.DatesBottomSheet
import com.example.wellcome.com.example.wellcome.RestrictionsBottomSheet
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.FragmentTripBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_trip.*
import android.view.WindowManager
import androidx.fragment.app.viewModels
import android.R
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.services.TripPattern
import com.example.wellcome.repository.Address
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.activity_main.*


class TripFragment : Fragment() {
    private val viewModel: SharedTripViewModel by viewModels(ownerProducer = { this })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClickListeners()
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading : Boolean ->
            if(isLoading)
                progress_bar.show()
            else
                progress_bar.hide()
        })
    }

    private fun initClickListeners(){
        val colorInt = resources.getColor(R.color.white)
        textField_restrictions.setStartIconTintList(ColorStateList.valueOf(colorInt))

        editText_restrictions.setOnClickListener{
            val modalBottomSheet = RestrictionsBottomSheet()
            modalBottomSheet.show(childFragmentManager, RestrictionsBottomSheet.TAG)
        }

        editText_dates.setOnClickListener{
            val modalBottomSheet = DatesBottomSheet()
            modalBottomSheet.show(childFragmentManager, DatesBottomSheet.TAG)
        }

        editText_location.setOnClickListener{
            val modalBottomSheet = LocalisationBottomSheet()
            modalBottomSheet.show(childFragmentManager, LocalisationBottomSheet.TAG)
        }

        search_button.setOnClickListener{
            val pattern = TripPattern(
                viewModel.adults.value!!,
                viewModel.babies.value!!,
                viewModel.pets.value!!,
                viewModel.childs.value!!,
                viewModel.city.value?.address?.city!!,
                viewModel.city.value?.address?.country!!,
                viewModel.city.value?.address?.postcode!!,
                viewModel.city.value?.lon!!.toDouble(),
                viewModel.city.value?.lat!!.toDouble()
            )
            viewModel.loadHosts(pattern)
            System.out.println("ds")
            //val nav = Navigation.findNavController(requireActivity(), com.example.wellcome.R.id.nav_host_fragment)
            //nav.navigate(com.example.wellcome.R.id.navigate_to_hosts)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTripBinding.inflate(
            inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}