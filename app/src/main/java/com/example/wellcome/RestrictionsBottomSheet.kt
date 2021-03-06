package com.example.wellcome

import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.wellcome.R
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.RestrictionsBottomSheetContentBinding
import kotlinx.android.synthetic.main.top_app_bar.*

class RestrictionsBottomSheet : BaseBottomSheet() {
    private val viewModel: SharedTripViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    ): View {
        val binding = RestrictionsBottomSheetContentBinding
            .inflate(inflater, container, false)
        binding.tripRestrictionsInputs.viewModel = viewModel
        binding.tripRestrictionsInputs.lifecycleOwner = this
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
