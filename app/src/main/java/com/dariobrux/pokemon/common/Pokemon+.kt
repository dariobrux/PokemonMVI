package com.dariobrux.pokemon.common

import com.dariobrux.pokemon.data.datasource.database.PokemonEntity
import com.dariobrux.pokemon.domain.model.Pokemon

fun List<PokemonEntity>.toPokemonList() : List<Pokemon> {
    return this.map {
        Pokemon(it.name)
    }
}

fun List<Pokemon>.toPokemonEntityList() : List<PokemonEntity> {
    return this.map {
        PokemonEntity(it.name)
    }
}