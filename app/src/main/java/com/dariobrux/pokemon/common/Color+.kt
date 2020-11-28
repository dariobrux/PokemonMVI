package com.dariobrux.pokemon.common

import android.graphics.Color
import androidx.annotation.ColorInt

fun Int.withAlpha(alpha: Int): Int {
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)
    return Color.argb(alpha, red, green, blue)
}