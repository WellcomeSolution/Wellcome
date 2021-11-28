package com.example.wellcome.com.example.wellcome.utils

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.wellcome.R
import com.google.android.material.button.MaterialButton

fun MaterialButton.enable(){
    iconTint = ColorStateList.valueOf(Color.BLACK)
    strokeColor = ColorStateList.valueOf(Color.BLACK)
    isEnabled = true
}

fun MaterialButton.disable(){
    iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.grey_100))
    strokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.grey_100))
    isEnabled = false
}