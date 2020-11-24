package com.dariobrux.pokemon.data.repository

import com.dariobrux.pokemon.domain.model.Pokemon

interface IPokemonRepository {

    suspend fun getPokemonList(location: String? = null): List<Pokemon>

    suspend fun getPokemonDetail(id: String): Pokemon
}