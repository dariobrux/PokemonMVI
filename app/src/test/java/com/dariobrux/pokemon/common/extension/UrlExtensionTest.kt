package com.dariobrux.pokemon.common.extension

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class UrlExtensionTest : TestCase() {

    @Test
    fun `get offset parameter`() {
        val url = "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20"
        assertEquals(url.getOffsetParameter(), 40)
    }

    @Test
    fun `get null offset parameter`() {
        val url = "https://pokeapi.co/api/v2/pokemon?limit=20"
        assertEquals(url.getOffsetParameter(), null)
    }

    @Test
    fun `get limit parameter`() {
        val url = "https://pokeapi.co/api/v2/pokemon?offset=40&limit=20"
        assertEquals(url.getLimitParameter(), 20)
    }

    @Test
    fun `get null limit parameter`() {
        val url = "https://pokeapi.co/api/v2/pokemon?offset=40"
        assertEquals(url.getLimitParameter(), null)
    }

    @Test
    fun `get nothing from null url`() {
        val url : String ? = null
        assertEquals(url.getOffsetParameter(), null)
        assertEquals(url.getLimitParameter(), null)
    }
}