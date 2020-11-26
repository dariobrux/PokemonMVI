package com.dariobrux.pokemon.domain.usecase

import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.repository.IPokemonRepository
import com.dariobrux.pokemon.domain.model.Pokemon

class GetPokemonList(private val repository: IPokemonRepository) {

    suspend operator fun invoke(offset: Int, limit: Int): List<PokemonEntity> {
        return repository.getPokemonList(offset, limit)
    }
}