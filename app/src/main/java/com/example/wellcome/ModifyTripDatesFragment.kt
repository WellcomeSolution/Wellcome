package com.example.wellcome

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.daterangepicker.CalendarPickerView
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.FragmentModifyTripDatesBinding
import com.example.wellcome.databinding.FragmentModifyTripRestrictionsBinding
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.top_app_bar.*
import kotlinx.android.synthetic.main.trip_dates.*
import java.text.SimpleDateFormat
import java.util.*

class ModifyTripDatesFragment : Fragment() {
    private val viewModel: SharedTripViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDates()
        initClickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        val binding = FragmentModifyTripDatesBinding
            .inflate(inflater, container, false)
        binding.tripDates.viewModel = viewModel
        binding.tripDates.lifecycleOwner = this
        return binding.root
    }

    private fun initDates(){
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.MONTH, 1)

        calendar_view.init(Date(), nextYear.time, SimpleDateFormat("MMMM, YYYY", Locale.getDefault()))
            .inMode(CalendarPickerView.SelectionMode.RANGE)

        calendar_view.setOnDateSelectedListener(object : CalendarPickerView.OnDateSelectedListener {
            override fun onDateSelected(date: Date?) {
                if(calendar_view.selectedDates.isNotEmpty())
                    viewModel.updateDate(calendar_view.selectedDates)
            }

            override fun onDateUnselected(date: Date?) {

            }

        })
    }

    private fun initClickListeners(){
        top_app_bar.setOnClickListener{
            val nav = findNavController()
            nav.popBackStack()
        }
    }
}