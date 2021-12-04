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


class DatesBottomSheet : BottomSheetDialogFragment() {
    private lateinit var behaviour : BottomSheetBehavior<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDates()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        dialog.setOnKeyListener { arg0, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                behaviour.state = BottomSheetBehavior.STATE_COLLAPSED
                //dialog.dismiss()
            }
            true
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dates_bottom_sheet_content, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun initDates(){
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.MONTH, 1)

        calendar_view.init(Date(), nextYear.time, SimpleDateFormat("MMMM, YYYY", Locale.getDefault()))
            .inMode(CalendarPickerView.SelectionMode.RANGE)
    }
}
