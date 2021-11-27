package com.example.wellcome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.Window
import androidx.core.util.Pair
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.example.daterangepicker.CalendarPickerView
import kotlinx.android.synthetic.main.activity_dates.*
import kotlinx.android.synthetic.main.activity_rescrictions_form.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.time.temporal.TemporalAdjusters.firstDayOfYear
import java.time.temporal.TemporalAdjusters.lastDayOfYear

class DatesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        initAnimations()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dates)
        calendar_view.init(dates().startDate.time, dates().endDate.time) //
            .inMode(CalendarPickerView.SelectionMode.RANGE)

        top_app_bar_dates.findViewById<MaterialToolbar>(R.id.topAppBar).setNavigationOnClickListener {
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
        }
    }

    private fun dates() : DatesOffset {
        val firstDayOfLastYear = Calendar.getInstance()
        firstDayOfLastYear[Calendar.DATE] = 1
        firstDayOfLastYear[Calendar.MONTH] = 0
        firstDayOfLastYear[Calendar.YEAR] = Calendar.getInstance().get(Calendar.YEAR) - DatesOffset.minDate

        val lastDayOfNextYear = Calendar.getInstance()
        lastDayOfNextYear[Calendar.DATE] = 31
        lastDayOfNextYear[Calendar.MONTH] = 11
        lastDayOfNextYear[Calendar.YEAR] = Calendar.getInstance().get(Calendar.YEAR) + DatesOffset.maxDate

        return DatesOffset(firstDayOfLastYear, lastDayOfNextYear)
    }

    private fun initAnimations(){
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
        }

        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
        }

        findViewById<View>(android.R.id.content).transitionName = "shared_element_container_dates"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    }
}

data class DatesOffset(val startDate : Calendar, val endDate: Calendar)
{
    companion object {
        const val minDate = 1
        const val maxDate = 1
    }
}