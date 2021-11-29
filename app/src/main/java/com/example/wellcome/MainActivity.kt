package com.example.wellcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_form.*
import android.content.Intent
import android.util.Log
import android.view.Window
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.utils.CitiesHelper
import com.example.wellcome.utils.City
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private var listener: OnBottomSheetCallbacks? = null
    private val viewModel: SharedTripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        initAnimation()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelScope.launch {

        }

         .launch(Dispatchers.Main) {
                viewModel.cities = CitiesHelper.getCities(applicationContext)
        }

        /*sharedE = MaterialContainerTransform().apply {
            // The drawing view is the id of the view above which the container transform
            // will play in z-space.
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            // Set the color of the scrim to transparent as we also want to animate the
            // list fragment out of view
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }*/

        //test.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)


        /*editText_restrictions.setOnClickListener{
            /*val constraintSet = ConstraintSet()
            constraintSet.clone(main_container)
            constraintSet.connect(R.id.test, ConstraintSet.TOP, R.id.main_container, ConstraintSet.TOP, 0)*/

            /*val autoTransition = AutoTransition()
            autoTransition.duration = 3000*/
            //findNavController(R.id.nav_host_fragment).navigate(R.id.form_fragment)
            //TransitionManager.beginDelayedTransition(main_container, autoTransition)
            //closeBottomSheet()


            //val intent = Intent(this, RescrictionsActivityForm::class.java)
            //startActivity(intent, null)
        }*/


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

    private fun initAnimation(){
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
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