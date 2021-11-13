package com.example.wellcome

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import android.text.InputType
import android.transition.AutoTransition
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager

import android.view.MotionEvent

import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import androidx.constraintlayout.motion.widget.MotionLayout
import android.view.animation.AnticipateOvershootInterpolator








class MainActivity : AppCompatActivity() {
    private var listener: OnBottomSheetCallbacks? = null

    private val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.navigation_graph)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentNavigationFragment?.apply {

            exitTransition = MaterialFadeThrough().apply {
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }


        }

        b.setOnClickListener{


            /*val transition: Transition = ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(1.0f)
            transition.duration = 1500
            TransitionManager.beginDelayedTransition(test, transition)*/
            //con.applyTo(mroot as ConstraintLayout?)
            test.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            findNavController(R.id.nav_host_fragment).navigate(R.id.form_fragment)

        }

        supportActionBar?.elevation = 0f
        configureBackdrop()
    }

    fun setOnBottomSheetCallbacks(onBottomSheetCallbacks: OnBottomSheetCallbacks) {
        this.listener = onBottomSheetCallbacks
    }

    fun closeBottomSheet() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED

    }

    fun openBottomSheet() {
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private var mBottomSheetBehavior: BottomSheetBehavior<View?>? = null

    private fun configureBackdrop() {
        val fragment = supportFragmentManager.findFragmentById(R.id.filter_fragment)

        fragment?.view?.let {
            BottomSheetBehavior.from(it).let { bs ->
                bs.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        listener?.onStateChanged(bottomSheet, newState)
                    }
                })

                bs.state = BottomSheetBehavior.STATE_EXPANDED
                mBottomSheetBehavior = bs
            }
        }
    }
}