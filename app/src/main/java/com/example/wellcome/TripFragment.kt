package com.example.wellcome

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.FragmentTripBinding
import kotlinx.android.synthetic.main.fragment_trip.*
import android.R
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.services.TripPattern

import androidx.lifecycle.Lifecycle
import com.example.services.HostPresenter
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.utils.PopError
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialSharedAxis


class TripFragment : Fragment() {
    private val viewModel: SharedTripViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private val popError: PopError = PopError()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClickListeners()

        viewModel.hostPresenters.observe(viewLifecycleOwner, { hosts : ArrayList<HostPresenter> ->
            if(viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED){
                if(!hosts.isNullOrEmpty()){
                    val nav = Navigation.findNavController(requireActivity(), com.example.wellcome.R.id.nav_host_fragment)
                    val directions = NavigationFragmentDirections.navigateToHosts()
                    nav.navigate(directions)
                    viewModel.isLoading.value = false
                }
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initClickListeners(){
        val colorInt = ContextCompat.getColor(requireContext(), R.color.white)
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
            var adults =  viewModel.adults.value!!
            var babies = viewModel.babies.value!!
            var childs = viewModel.childs.value!!
            var lon  = viewModel.city.value?.lon!!

            if(popError.intTypeTest(adults)&&popError.intTypeTest(babies)&&popError.intTypeTest(childs)){
                Snackbar.make(search_button,"Please select travelers!",Snackbar.LENGTH_LONG)
                    .show()
            }
            else if(popError.stringTypeTest(lon)){
                Snackbar.make(search_button,"Please select your location!",Snackbar.LENGTH_LONG).show()
            }
            //manque le date
            else{
                val pattern = TripPattern(
                    userViewModel.email.value!!,
                    viewModel.adults.value!!,
                    viewModel.babies.value!!,
                    viewModel.childs.value!!,
                    viewModel.city.value?.lon!!.toDouble(),
                    viewModel.city.value?.lat!!.toDouble()
                )
                viewModel.loadHosts(pattern)
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTripBinding.inflate(
            inflater, container, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}