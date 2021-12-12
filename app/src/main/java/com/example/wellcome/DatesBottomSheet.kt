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
import androidx.fragment.app.viewModels
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.DatesBottomSheetContentBinding
import com.example.wellcome.databinding.RestrictionsBottomSheetContentBinding
import kotlinx.android.synthetic.main.top_app_bar.*
import utils.DateUtils
import java.time.Instant
import java.time.ZoneId


class DatesBottomSheet : BottomSheetDialogFragment() {
    private val viewModel: SharedTripViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private lateinit var behaviour : BottomSheetBehavior<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        initDates()
        initClickListeners()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                behaviour = BottomSheetBehavior.from(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
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
        viewModel.updateDate(calendar_view.selectedDates)
        super.onDismiss(dialog)
    }
}
