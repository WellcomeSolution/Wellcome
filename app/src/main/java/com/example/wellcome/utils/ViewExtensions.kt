package com.example.wellcome.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.example.wellcome.R
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_rescrictions_form.*

fun View.disable() {
    background.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
    isClickable = false
}

fun View.scaleDown(){
    val scaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, 1.0f, 0.9f).setDuration(100)
    val scaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, 1.0f, 0.9f).setDuration(100)
    val downscaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, 0.9f, 1.0f).setDuration(100)
    val downscaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, 0.9f, 1.0f).setDuration(100)

    val set = AnimatorSet()
    set.playTogether(scaleX, scaleY)
    set.play(downscaleX).after(scaleX)
    set.playTogether(downscaleX, downscaleY)

    set.start()
}
