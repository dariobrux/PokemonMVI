package com.dariobrux.pokemon.common.extension

import android.view.View

/**
 *
 * Created by Dario Bruzzese on 27/11/2020.
 *
 * This file contains all View extended methods.
 *
 */

/**
 * Set the visibility to VISIBLE.
 */
fun View.toVisible() {
    this.visibility = View.VISIBLE
}

/**
 * Set the visibility to GONE.
 */
fun View.toGone() {
    this.visibility = View.GONE
}