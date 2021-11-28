package com.example.wellcome

import android.app.Activity
import android.app.ActivityOptions
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.wellcome.com.example.wellcome.data.RestrictionsFormViewModel
import com.example.wellcome.com.example.wellcome.data.TripFormViewModel
import com.example.wellcome.databinding.ActivityRescrictionsFormBinding
import com.example.wellcome.utils.City
import com.example.wellcome.databinding.FragmentTripBinding
import com.example.wellcome.models.HostRestrictions
import kotlinx.android.synthetic.main.fragment_trip.*
import com.google.android.material.transition.MaterialContainerTransform





class TripFragment : Fragment() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(TripFormViewModel::class.java) }

    private val openRestrictionsFormActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bundle = result.data?.extras
                viewModel.hostRestrictions.value = bundle?.getSerializable("Restrictions") as HostRestrictions
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAnimations()
        initClickListeners()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initClickListeners(){
        editText_restrictions.setOnClickListener{
            val intent = Intent(context, RescrictionsFormActivity::class.java)
            val options =  ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                editText_restrictions,
                "shared_element_container_restrictions"  // The transition name to be matched in Activity B.
            )
            openRestrictionsFormActivity.launch(intent, options)
        }

        editText_dates.setOnClickListener{
            val intent = Intent(context, DatesActivity::class.java)
            val options =  ActivityOptions.makeSceneTransitionAnimation(
                activity,
                editText_dates,
                "shared_element_container_dates"  // The transition name to be matched in Activity B.
            )
            activity?.startActivity(intent, options.toBundle())
        }

        editText_location.setOnClickListener{
            val intent = Intent(context, SearchCityActivity::class.java)
            val options =  ActivityOptions.makeSceneTransitionAnimation(
                activity,
                editText_location,
                "shared_element_container_location"  // The transition name to be matched in Activity B.
            )
            activity?.startActivity(intent, options.toBundle())
        }
    }

    private fun initAnimations(){
        sharedElementEnterTransition = MaterialContainerTransform().setDuration(300L)
        sharedElementReturnTransition = MaterialContainerTransform().setDuration(300L)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTripBinding.inflate(
            inflater, container, false)

        binding.viewModel = viewModel

        return binding.root
    }
}