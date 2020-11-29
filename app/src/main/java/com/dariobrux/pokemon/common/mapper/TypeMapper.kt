package com.dariobrux.pokemon.common.mapper

import android.graphics.Color

object TypeMapper {

    fun getTypeColor(name: String): Int {
        return Color.parseColor(
            when (name) {
                "bug" -> "#92BC2C"
                "dark" -> "#5A5761"
                "dragon" -> "#0D69C7"
                "electric" -> "#F3D94E"
                "fairy" -> "#EE91E6"
                "fighting" -> "#D3425F"
                "fire" -> "#FCA54C"
                "flying" -> "#A0BBEC"
                "ghost" -> "#5F6EBC"
                "grass" -> "#60BD58"
                "ground" -> "#D97C4C"
                "ice" -> "#75D0C1"
                "normal" -> "#A0A29F"
                "poison" -> "#B763CF"
                "psychic" -> "#FA8581"
                "rock" -> "#C9BB8A"
                "steel" -> "#5694A3"
                "water" -> "#559CDF"
                else -> "#ffffff"
            }
        )
    }
}