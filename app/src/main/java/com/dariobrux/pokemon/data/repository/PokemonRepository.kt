package com.dariobrux.pokemon.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dariobrux.pokemon.data.datasource.database.PokemonDAO
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.datasource.webservice.PokemonApi
import com.dariobrux.pokemon.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 *
 * Created by Dario Bruzzese on 26/11/2020.
 *
 * This class is the repository that handles the communication
 * between the restful api and the database.
 *
 */
class PokemonRepository(private val api: PokemonApi, private val dao: PokemonDAO) : IPokemonRepository {

    var pagerPokemon: Pager<Int, PokemonEntity>? = null

    override var state: MutableLiveData<State> = MutableLiveData(State.DEFAULT)

    /**
     * Get the Pokemon list with paging.
     */
    override suspend fun getPokemonList(): Flow<PagingData<PokemonEntity>>? {

        pagerPokemon = Pager(PagingConfig(pageSize = 10)) {
            PokemonDataSource(api, dao, state)
        }

        return pagerPokemon?.flow
    }
}