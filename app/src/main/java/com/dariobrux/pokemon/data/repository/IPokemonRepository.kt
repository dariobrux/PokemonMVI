package com.dariobrux.pokemon.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

/**
 *
 * Created by Dario Bruzzese on 24/11/2020.
 *
 */
interface IPokemonRepository {

    /**
     * Get the Pokemon list with paging.
     */
    suspend fun getPokemonList(): Flow<PagingData<PokemonEntity>>?

    /**
     * The state of the request
     */
    val state: LiveData<State>
}