package com.example.wellcome

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.ActivityRescrictionsFormBinding
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class RescrictionsFormActivity : AppCompatActivity() {
    private val viewModel: SharedTripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        initAnimations()
        super.onCreate(savedInstanceState)
        Log.e("tag", viewModel.adults.value.toString())
        //val binding :ActivityRescrictionsFormBinding = DataBindingUtil.setContentView(
          //  this, R.layout.activity_rescrictions_form)

        //binding.lifecycleOwner = this
        //binding.viewModel = viewModel

        initClickListeners()
    }

    override fun onBackPressed() {
        /*val bundle = Bundle()
        bundle.putSerializable("Restrictions",
            com.example.services.HostTravelers(
                viewModel.adults.value, viewModel.babies.value,
                viewModel.pets.value, viewModel.childs.value))
        setResult(Activity.RESULT_OK, intent.putExtras(bundle))
        super.onBackPressed()*/
    }

    private fun initClickListeners() {

    }

    private fun initAnimations(){
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
            scrimColor = Color.TRANSPARENT
            //setAllContainerColors(applicationContext.themeColor(R.attr.colorSurface))
        }

        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
            scrimColor = Color.TRANSPARENT
            //setAllContainerColors(applicationContext.themeColor(R.attr.colorSurface))
        }

        findViewById<View>(android.R.id.content).transitionName = "shared_element_container_restrictions"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    }
}