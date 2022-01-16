package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wellcome.databinding.FragmentCreateTripStepperFormBinding
import com.google.android.material.transition.MaterialSharedAxis

class CreateTripStepperFormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCreateTripStepperFormBinding.inflate(
            inflater, container, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        //binding.viewModel = viewModel
        //binding.lifecycleOwner = this
        return binding.root
    }
}