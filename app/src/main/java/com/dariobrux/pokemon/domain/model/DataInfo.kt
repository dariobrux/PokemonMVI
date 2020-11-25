package com.dariobrux.pokemon.domain.model

import com.google.gson.annotations.SerializedName

class DataInfo {
    @SerializedName("results")
    var pokemonList: List<Pokemon>? = null
}