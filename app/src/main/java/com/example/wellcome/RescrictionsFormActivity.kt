package com.example.wellcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wellcome.databinding.ActivityRescrictionsFormBinding
import com.example.wellcome.utils.Animations
import com.example.wellcome.utils.db.HostRestrictions
import com.example.wellcome.utils.disable
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_rescrictions_form.*

class RescrictionsFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRescrictionsFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initAnimations()

        super.onCreate(savedInstanceState)

        val binding: ActivityRescrictionsFormBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_rescrictions_form)
        setContentView(binding.root)

        binding.restrictions = HostRestrictions(0, 0, 0, 0)

        /*app_bar.setOnClickListener{
            Animations.getScaleDownAnimation(remove_adult).start()
        }*/

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
        }

        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
        }

        findViewById<View>(android.R.id.content).transitionName = "shared_element_container_restrictions"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    }
}