package com.example.wellcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
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
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_rescrictions_form.*

class RescrictionsActivityForm : AppCompatActivity() {
    private lateinit var binding: ActivityRescrictionsFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityRescrictionsFormBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_rescrictions_form)
        setContentView(binding.root)

        binding.restrictions = HostRestrictions(0, 0, 0, 0)

        /*app_bar.setOnClickListener{
            Animations.getScaleDownAnimation(remove_adult).start()
        }*/

        topAppBar.setNavigationOnClickListener {
            finish()
        }
    }
}