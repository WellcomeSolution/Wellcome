package com.example.wellcome.com.example.wellcome.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.wellcome.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

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

@BindingAdapter("app:startIconTint")
fun TextInputLayout.setStartIconTint(@ColorInt color: Int) {
    setStartIconTintList(ColorStateList.valueOf(color))
}