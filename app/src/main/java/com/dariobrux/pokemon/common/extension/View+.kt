package com.dariobrux.pokemon.common.extension

import android.animation.ValueAnimator
import androidx.cardview.widget.CardView

/**
 * Animate the card background color of a [CardView] starting from a color, ending to another color using
 * the argb evaluator.
 * @param startColor the start color.
 * @param toColor the final color.
 */
fun CardView.animateCardBackgroundColor(startColor: Int, toColor: Int) {
    val anim: ValueAnimator = ValueAnimator.ofArgb(startColor, toColor)
    anim.duration = 0
    anim.addUpdateListener {
        val color = it.animatedValue as Int
        this.setCardBackgroundColor(color)
    }
    anim.start()
}