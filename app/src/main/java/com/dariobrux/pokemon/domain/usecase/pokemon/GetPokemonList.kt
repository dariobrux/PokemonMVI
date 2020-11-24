package com.dariobrux.pokemon.domain.usecase.pokemon

import com.dariobrux.pokemon.data.repository.IPokemonRepository
import com.dariobrux.pokemon.domain.model.Pokemon

class GetPokemonList(private val IPokemonRepository: IPokemonRepository) {

    suspend operator fun invoke(): List<Pokemon> {
        return IPokemonRepository.getPokemonList()
    }
}