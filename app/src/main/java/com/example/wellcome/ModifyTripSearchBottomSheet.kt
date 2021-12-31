package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.wellcome.databinding.LocalisationBottomSheetContentBinding
import com.example.wellcome.databinding.ModifyTripSearchBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_trip_configuration_modify.*

class ModifyTripSearchBottomSheet : BaseBottomSheet() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ModifyTripSearchBottomSheetContentBinding
            .inflate(inflater, container, false)
        //setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}