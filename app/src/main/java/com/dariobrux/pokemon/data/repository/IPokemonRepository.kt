package com.dariobrux.pokemon.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    suspend fun getPokemonList(): Flow<PagingData<PokemonEntity>>?

    val state: LiveData<State>

    enum class State {
        DEFAULT,
        LOADING,
        LOADED,
        ERROR
    }
}