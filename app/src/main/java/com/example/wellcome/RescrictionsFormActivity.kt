package com.example.wellcome

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.wellcome.com.example.wellcome.data.RestrictionsFormViewModel
import com.example.wellcome.com.example.wellcome.utils.disable
import com.example.wellcome.com.example.wellcome.utils.enable
import com.example.wellcome.databinding.ActivityRescrictionsFormBinding
import com.example.wellcome.utils.scaleDown
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_rescrictions_form.*

class RescrictionsFormActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(RestrictionsFormViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        initAnimations()
        super.onCreate(savedInstanceState)

        val binding :ActivityRescrictionsFormBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_rescrictions_form)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        suscribeEvents()
        initClickListeners()
    }

    private fun suscribeEvents(){
        RestrictionsFormViewModel.travelersRemoved += ::travelersRemoved
        RestrictionsFormViewModel.travelersAdded += :: travelersAdded

        RestrictionsFormViewModel.childsRemoved += ::childsRemoved
        RestrictionsFormViewModel.childsAdded += :: childsAdded

        RestrictionsFormViewModel.babiesRemoved += ::babiesRemoved
        RestrictionsFormViewModel.babiesAdded += :: babiesAdded

        RestrictionsFormViewModel.petsRemoved += ::petsRemoved
        RestrictionsFormViewModel.petsAdded += :: petsAdded
    }

    private fun travelersRemoved(int:Int) {
        remove_travelers.scaleDown()
        updateButton(remove_travelers, viewModel.travelers.value!!)
    }

    private fun travelersAdded(int:Int) {
        add_travelers.scaleDown()
        updateButton(remove_travelers, viewModel.travelers.value!!)
    }

    private fun childsRemoved(int:Int) {
        remove_children.scaleDown()
        updateButton(remove_children, viewModel.childs.value!!)
    }

    private fun childsAdded(int:Int) {
        add_children.scaleDown()
        updateButton(remove_children, viewModel.childs.value!!)
    }

    private fun babiesRemoved(int:Int) {
        remove_baby.scaleDown()
        updateButton(remove_baby, viewModel.babies.value!!)
    }

    private fun babiesAdded(int:Int) {
        add_baby.scaleDown()
        updateButton(remove_baby, viewModel.babies.value!!)
    }

    private fun petsRemoved(int:Int) {
        remove_pet.scaleDown()
        updateButton(remove_pet, viewModel.pets.value!!)
    }

    private fun petsAdded(int:Int) {
        add_pet.scaleDown()
        updateButton(remove_pet, viewModel.pets.value!!)
    }

    private fun updateButton(button:MaterialButton, value:Int){
        if(value > 0)
            button.enable()
        else
            button.disable()
    }

    private fun initClickListeners() {
        top_app_bar_restrictions.findViewById<MaterialToolbar>(R.id.topAppBar).setNavigationOnClickListener {
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
            dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
        }
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