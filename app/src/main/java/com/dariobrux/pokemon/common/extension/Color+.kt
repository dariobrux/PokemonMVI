package com.dariobrux.pokemon.common.extension

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 *
 * Created by Dario Bruzzese on 29/11/2020.
 *
 * This file contains all the Color object (as Int) extended methods.
 *
 */

/**
 * Get the same color applying alpha.
 * @param alpha to apply to the color.
 * @return the new color.
 */
fun Int.withAlpha(alpha: Int): Int {
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)
    return Color.argb(alpha, red, green, blue)
}