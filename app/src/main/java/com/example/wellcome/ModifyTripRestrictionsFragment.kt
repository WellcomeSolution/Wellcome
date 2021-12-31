package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.FragmentModifyTripRestrictionsBinding
import com.example.wellcome.databinding.RestrictionsBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.fragment_trip_configuration_modify.*

class ModifyTripRestrictionsFragment : Fragment() {
    private val viewModel: SharedTripViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        val binding = FragmentModifyTripRestrictionsBinding
            .inflate(inflater, container, false)
        binding.tripRestrictionsInputs.viewModel = viewModel
        binding.tripRestrictionsInputs.lifecycleOwner = this
        return binding.root
    }

}