package com.dariobrux.pokemon.domain.model

data class PokemonInfo(

    var id: Int,

    var name: String,

    var types: List<Map<Any, Any>>,

    var stats: List<Map<Any, Any>>,

    )
