package com.dariobrux.pokemon.common.mapper

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class StatsMapperTest : TestCase() {

    @Test
    fun `attack abbreviation`() {
        val result = StatsMapper.getStatsAbbreviationColor("attack")
        assertEquals("ATK", result.first)
    }

    @Test
    fun `defence abbreviation`() {
        val result = StatsMapper.getStatsAbbreviationColor("defense")
        assertEquals("DEF", result.first)
    }

    @Test
    fun `special-attack abbreviation`() {
        val result = StatsMapper.getStatsAbbreviationColor("special-attack")
        assertEquals("SP. ATK", result.first)
    }

    @Test
    fun `special-defense abbreviation`() {
        val result = StatsMapper.getStatsAbbreviationColor("special-defense")
        assertEquals("SP. DEF", result.first)
    }

    @Test
    fun `speed abbreviation`() {
        val result = StatsMapper.getStatsAbbreviationColor("speed")
        assertEquals("SPD", result.first)
    }
}