package com.example.wellcome

import android.os.Bundle
import com.example.daterangepicker.CalendarPickerView
import com.example.wellcome.R
import java.text.SimpleDateFormat
import java.util.*
import android.content.DialogInterface
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.DatesBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dates_bottom_sheet_content.*
import kotlinx.android.synthetic.main.top_app_bar.*


class DatesBottomSheet : BaseBottomSheet() {
    private val viewModel: SharedTripViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        initDates()
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
    ) : View {
        val binding = DatesBottomSheetContentBinding
            .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private fun initDates(){
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.MONTH, 1)

        calendar_view.init(Date(), nextYear.time, SimpleDateFormat("MMMM, YYYY", Locale.getDefault()))
            .inMode(CalendarPickerView.SelectionMode.RANGE)
    }

    override fun onDismiss(dialog: DialogInterface) {
        if(calendar_view.selectedDates.isNotEmpty())
            viewModel.updateDate(calendar_view.selectedDates)
        super.onDismiss(dialog)
    }
}
