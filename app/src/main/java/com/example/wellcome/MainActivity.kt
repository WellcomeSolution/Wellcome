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
import com.example.wellcome.utils.CitiesHelper
import com.example.wellcome.utils.City
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private var listener: OnBottomSheetCallbacks? = null

    companion object {
        lateinit var cities : Collection<City>
    }

    private val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.navigation_graph)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Launch a coroutine that by default goes to the main thread
        GlobalScope.launch(Dispatchers.Main) {
            // Switch to a background (IO) thread
            val retval = withContext(Dispatchers.IO) {
                Log.e("TASK", "Started background task")
                val retval = "The value from background"
                cities = CitiesHelper.getCities(applicationContext)
                Log.e("TASK", "Finished background task with result: " + retval)
                retval
            }
            // Now you're back the main thread
            Log.e("TASK", "Started task in Main thread with result from Background: " + retval)
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