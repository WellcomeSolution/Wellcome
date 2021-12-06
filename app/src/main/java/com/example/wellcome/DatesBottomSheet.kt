package com.example.wellcome.com.example.wellcome

import android.app.Dialog
import android.os.Bundle
import com.example.daterangepicker.CalendarPickerView
import com.example.wellcome.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_dates_form.*
import kotlinx.android.synthetic.main.activity_dates_form.calendar_view
import kotlinx.android.synthetic.main.dates_bottom_sheet_content.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.DialogInterface
import android.view.*
import kotlinx.android.synthetic.main.top_app_bar.*


class DatesBottomSheet : BottomSheetDialogFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDates()
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
    ): View? = inflater.inflate(R.layout.dates_bottom_sheet_content, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private fun initDates(){
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.MONTH, 1)

        calendar_view.init(Date(), nextYear.time, SimpleDateFormat("MMMM, YYYY", Locale.getDefault()))
            .inMode(CalendarPickerView.SelectionMode.RANGE)
    }
}
