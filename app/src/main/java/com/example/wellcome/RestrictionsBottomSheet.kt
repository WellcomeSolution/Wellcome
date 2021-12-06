package com.example.wellcome.com.example.wellcome

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.wellcome.R
import com.example.wellcome.TripFragment
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.RestrictionsBottomSheetContentBinding
import com.example.wellcome.utils.scaleDown
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.restrictions_bottom_sheet_content.*
import kotlinx.android.synthetic.main.top_app_bar.*

class RestrictionsBottomSheet : BottomSheetDialogFragment() {
    private val viewModel: SharedTripViewModel by viewModels(ownerProducer = { requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onAddAdults()
        initClickListeners()
    }

    private fun initClickListeners(){
        top_app_bar.setOnClickListener{
            this.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RestrictionsBottomSheetContentBinding
            .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
