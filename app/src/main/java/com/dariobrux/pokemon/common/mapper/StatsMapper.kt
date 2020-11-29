package com.dariobrux.pokemon.common.mapper

import android.graphics.Color
import java.util.*

object StatsMapper {

    fun getStatsAbbreviationColor(name: String): Pair<String, Int> {
        return when (name.toLowerCase(Locale.getDefault())) {
            "attack" -> "ATK" to Color.parseColor("#f06292") // Pink 300
            "defense" -> "DEF" to Color.parseColor("#7986cb") // Indigo 300
            "special-attack" -> "SP. ATK" to Color.parseColor("#4db6ac") // Teal 300
            "special-defense" -> "SP. DEF" to Color.parseColor("#ffd54f") // Amber 300
            "speed" -> "SPD" to Color.parseColor("#a1887f") // Brown 300
            else -> name to Color.parseColor("#4dd0e1") // Cyan 300
        }
    }
}