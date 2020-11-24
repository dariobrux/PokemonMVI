package com.dariobrux.pokemon.domain.usecase.pokemon

class LoadPokemonList(private val getPokemonList: GetPokemonList) {

    suspend operator fun invoke() {
        getPokemonList()
    }
}