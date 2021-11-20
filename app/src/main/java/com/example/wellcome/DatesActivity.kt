package com.example.wellcome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.Window
import androidx.core.util.Pair
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class DatesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initAnimations()



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dates)
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .setSelection(
                    Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                )
                .build()

        dateRangePicker.show(supportFragmentManager, "tag")

    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {


        return super.onCreateView(name, context, attrs)


    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()


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

        findViewById<View>(android.R.id.content).transitionName = "shared_element_container"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    }
}