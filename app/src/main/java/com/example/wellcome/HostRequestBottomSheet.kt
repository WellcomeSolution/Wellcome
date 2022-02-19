package com.example.wellcome

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import com.example.wellcome.data.SharedTripViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

import androidx.navigation.navGraphViewModels
import com.example.services.HostReservationDto
import com.example.wellcome.data.HostRequestViewModel
import com.example.wellcome.data.HostViewModel
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.HostRequestBottomSheetContentBinding
import kotlinx.android.synthetic.main.host_request_bottom_sheet_content.*


class HostRequestBottomSheet : BaseBottomSheet() {
    private val hostViewModel: HostViewModel by navGraphViewModels(R.id.hostFragment)
    private val sharedTripViewModel: SharedTripViewModel by activityViewModels()
    private val hostRequestViewModel: HostRequestViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet = (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()

        send_reservation_button.setOnClickListener{
            hostRequestViewModel.sendHostReservation(
                HostReservationDto(
                    userViewModel.email.value!!,
                    hostRequestViewModel.message.value!!,
                    userViewModel.phone.value!!,
                    hostViewModel.hostUuid.value!!,
                    null
                )
            )
            this.dismiss()
        }
    }

    private fun initLayout(){
        val displayMetrics = context?.resources?.displayMetrics
        val height = displayMetrics?.heightPixels
        content.minimumHeight = height!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HostRequestBottomSheetContentBinding
            .inflate(inflater, container, false)
        binding.hostRequestViewModel = hostRequestViewModel
        binding.sharedTripViewModel = sharedTripViewModel
        binding.hostViewModel = hostViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
