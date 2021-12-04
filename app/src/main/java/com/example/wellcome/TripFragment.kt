package com.example.wellcome

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





class TripFragment : Fragment() {
    private lateinit var bottomSheetBehavior:BottomSheetBehavior<View>

    private val viewModel: SharedTripViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClickListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initClickListeners(){
        /*editText_restrictions.setOnClickListener{
            /*val intent = Intent(context, RescrictionsFormActivity::class.java)
            val options =  ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                editText_restrictions,
                "shared_element_container_restrictions"  // The transition name to be matched in Activity B.
            )
            activity?.startActivity(intent, options.toBundle())
*/
            childFragmentManager
                .beginTransaction()
                // Map the start View in FragmentA and the transitionName of the end View in FragmentB
                .replace(R.id.nav_host_fragment, FormFragment(), "WindowFragment.TAG")
                .addToBackStack("FragmentB.TAG")
                .commit()
        }*/

        editText_restrictions.setOnClickListener{
            val modalBottomSheet = RestrictionsBottomSheet()
            modalBottomSheet.show(parentFragmentManager, RestrictionsBottomSheet.TAG)
        }

        editText_dates.setOnClickListener{
            val modalBottomSheet = DatesBottomSheet()
            modalBottomSheet.show(parentFragmentManager, DatesBottomSheet.TAG)
        }

        editText_location.setOnClickListener{

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentTripBinding.inflate(
            inflater, container, false)

        binding.viewModel = viewModel

        return binding.root
    }
}