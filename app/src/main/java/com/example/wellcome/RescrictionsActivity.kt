package com.example.wellcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wellcome.databinding.ActivityRescrictionsBinding
import com.example.wellcome.utils.Animations
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_rescrictions.*

class RescrictionsActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRescrictionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // Enable Activity Transitions. Optionally enable Activity transitions in your
        // theme with <item name=”android:windowActivityTransitions”>true</item>.
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        // Set the transition name, which matches Activity A’s start view transition name, on
        // the root view.
        findViewById<View>(android.R.id.content).transitionName = "shared_element_container"

        // Attach a callback used to receive the shared elements from Activity A to be
        // used by the container transform transition.
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        // Set this Activity’s enter and return transition to a MaterialContainerTransform
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 300L
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 250L
        }

        super.onCreate(savedInstanceState)

        binding = ActivityRescrictionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        remove_adult.setOnClickListener{
            Animations.getScaleDownAnimation(remove_adult).start()
        }
    }
}