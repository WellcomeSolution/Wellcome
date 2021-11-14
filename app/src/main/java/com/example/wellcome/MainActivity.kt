package com.example.wellcome

import android.animation.LayoutTransition
import android.content.Context
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
import android.util.AttributeSet

import android.view.MotionEvent

import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import androidx.constraintlayout.motion.widget.MotionLayout
import android.view.animation.AnticipateOvershootInterpolator
import androidx.navigation.fragment.FragmentNavigatorExtras
import kotlinx.android.synthetic.main.fragment_form.*
import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.view.Window
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback


class MainActivity : AppCompatActivity() {
    private var listener: OnBottomSheetCallbacks? = null

    private val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.navigation_graph)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    private val currentTopBarNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.top_bar_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentNavigationFragment?.apply {
            exitTransition = MaterialFadeThrough().apply {
                duration = resources.getInteger(R.integer.reply_motion_duration_medium).toLong()
            }
        }

        //test.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)


        editText_restrictions.setOnClickListener{
            /*val constraintSet = ConstraintSet()
            constraintSet.clone(main_container)
            constraintSet.connect(R.id.test, ConstraintSet.TOP, R.id.main_container, ConstraintSet.TOP, 0)*/

            /*val autoTransition = AutoTransition()
            autoTransition.duration = 3000*/
            //findNavController(R.id.nav_host_fragment).navigate(R.id.form_fragment)
            //TransitionManager.beginDelayedTransition(main_container, autoTransition)
            //closeBottomSheet()

            val intent = Intent(this, RescrictionsActivity::class.java)

            /*val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                editText_restrictions,
                "shared_element_container" // The transition name to be matched in Activity B.
            )*/
            startActivity(intent, null)
        }


        /*bb.setOnClickListener{
            findNavController(R.id.top_bar_fragment).navigate(R.id.app_bar_fragment)
        }*/

        /*b.setOnClickListener{


            /*val transition: Transition = ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(1.0f)
            transition.duration = 1500
            TransitionManager.beginDelayedTransition(test, transition)*/
            //con.applyTo(mroot as ConstraintLayout?)

            findNavController(R.id.nav_host_fragment).navigate(R.id.form_fragment)

        }*/

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
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {

                    }

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