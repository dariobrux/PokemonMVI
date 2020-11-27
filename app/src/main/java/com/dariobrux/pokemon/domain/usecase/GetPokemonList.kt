package com.dariobrux.pokemon.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.repository.IPokemonRepository
import com.dariobrux.pokemon.domain.model.Pokemon
import com.dariobrux.pokemon.domain.model.root.Results
import com.dariobrux.pokemon.domain.model.root.RootData
import kotlinx.coroutines.flow.Flow

class GetPokemonList(private val repository: IPokemonRepository) {

    suspend operator fun invoke(offset: Int, limit: Int): Flow<PagingData<PokemonEntity>>? {
        return repository.getPokemonList(offset, limit)
    }
}