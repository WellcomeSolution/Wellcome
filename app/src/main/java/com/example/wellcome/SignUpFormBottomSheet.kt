package com.example.wellcome

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.LocalisationBottomSheetContentBinding
import com.example.wellcome.databinding.SignUpFormBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_trip_configuration_modify.*
import kotlinx.android.synthetic.main.localisation_bottom_sheet_content.*
import kotlinx.android.synthetic.main.modify_trip_search_bottom_sheet_content.*
import kotlinx.android.synthetic.main.sign_up_form_bottom_sheet_content.*
import java.util.*

class SignUpFormBottomSheet : BaseBottomSheet() {
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()

        birthdate_edit_text.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                { view, year, monthOfYear, dayOfMonth ->
                    userViewModel.birthDate.postValue("$day-$month-$year")
                }, year, month, day)
            datePicker.show()
        }
    }

    private fun initLayout(){
        val displayMetrics = context?.resources?.displayMetrics
        val height = displayMetrics?.heightPixels
        sign_up_container.minimumHeight = height!!
    }

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SignUpFormBottomSheetContentBinding
            .inflate(inflater, container, false)
        binding.viewModel = userViewModel
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}