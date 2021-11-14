package com.example.wellcome.utils

import android.graphics.BlendMode
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat

fun View.disable() {
    getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
    setClickable(false)
}