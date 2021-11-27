package com.example.wellcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.Window
import com.example.daterangepicker.CalendarPickerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_dates_form.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DatesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initAnimations()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dates_form)
        initDates()
        initClickListener()
    }

    private fun initClickListener(){
        top_app_bar_dates.findViewById<MaterialToolbar>(R.id.topAppBar).setNavigationOnClickListener {
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
        }
    }

    private fun initDates(){
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.MONTH, 2)

        calendar_view.init(Date(), nextYear.time, SimpleDateFormat("MMMM, YYYY", Locale.getDefault()))
            .inMode(CalendarPickerView.SelectionMode.RANGE)
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

    fun getDaysBetweenDates(startDate: Date?, endDate: Date?): List<Date>? {
        val dates: MutableList<Date> = ArrayList()
        val calendar: Calendar = GregorianCalendar()
        calendar.time = startDate
        while (calendar.time.before(endDate)) {
            val result = calendar.time
            dates.add(result)
            calendar.add(Calendar.DATE, 1)
        }
        return dates
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