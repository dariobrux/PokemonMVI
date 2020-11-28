package com.dariobrux.pokemon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.domain.model.Pokemon
import com.dariobrux.pokemon.domain.model.root.Results
import com.dariobrux.pokemon.domain.model.root.RootData
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    suspend fun getPokemonList(offset: Int, limit: Int): Flow<PagingData<PokemonEntity>>?

    suspend fun getPokemonDetail(id: String): Pokemon
}