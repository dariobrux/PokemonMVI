package com.dariobrux.pokemon.domain.usecase

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.repository.IPokemonRepository
import com.dariobrux.pokemon.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonList(private val repository: IPokemonRepository) {

    var state = repository.state

    suspend operator fun invoke(offset: Int, limit: Int): Flow<PagingData<PokemonEntity>>? {
        return repository.getPokemonList(offset, limit)
    }
}