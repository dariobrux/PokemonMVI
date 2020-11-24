package com.dariobrux.pokemon.domain.usecase.pokemon

class LoadPokemonInfo(private val getPokemonInfo: GetPokemonInfo) {

    suspend operator fun invoke(id: String) {
        getPokemonInfo(id)
    }
}