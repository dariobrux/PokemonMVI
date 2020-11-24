package com.dariobrux.pokemon.domain.usecase

class LoadPokemonList(private val getPokemonList: GetPokemonList) {

    suspend operator fun invoke() {
        getPokemonList()
    }
}