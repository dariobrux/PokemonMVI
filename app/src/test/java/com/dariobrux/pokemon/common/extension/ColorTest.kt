package com.dariobrux.pokemon.common.extension

import android.graphics.Color
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class ColorTest : TestCase() {

    @Test
    fun `color to same color with custom alpha`() {
        val red = Color.RED
        val redWithAlpha = red.withAlpha(80)
        assertEquals(Color.argb(80, 255, 0, 0), redWithAlpha)
    }
}