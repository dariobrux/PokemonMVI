package com.dariobrux.pokemon.domain.usecase

import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow

/**
 *
 * Created by Dario Bruzzese on 24/11/2020.
 *
 * This class represent the Pokemon getter use case.
 *
 */
class GetPokemonList(private val repository: IPokemonRepository) {

    var state = repository.state

    suspend operator fun invoke(): Flow<PagingData<PokemonEntity>>? {
        return repository.getPokemonList()
    }
}