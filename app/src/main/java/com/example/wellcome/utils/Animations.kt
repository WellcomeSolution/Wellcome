package com.example.wellcome.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

class Animations {
    companion object{
        fun getScaleDownAnimation(view:View) : AnimatorSet {
            val scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.0f, 0.9f).setDuration(100)
            val scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.0f, 0.9f).setDuration(100)
            val downscaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.9f, 1.0f).setDuration(100)
            val downscaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.9f, 1.0f).setDuration(100)

            val set = AnimatorSet()
            set.playTogether(scaleX, scaleY)
            set.play(downscaleX).after(scaleX)
            set.playTogether(downscaleX, downscaleY)

            return set
        }
    }
}