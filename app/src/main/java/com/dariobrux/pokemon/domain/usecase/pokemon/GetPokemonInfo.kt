package com.dariobrux.pokemon.domain.usecase.pokemon

import com.dariobrux.pokemon.data.repository.IPokemonRepository
import com.dariobrux.pokemon.domain.model.Pokemon

class GetPokemonInfo(private val IPokemonRepository: IPokemonRepository) {

    suspend operator fun invoke(id: String): Pokemon {
        return IPokemonRepository.getPokemonDetail(id)
    }
}