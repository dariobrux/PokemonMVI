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

class PokemonRepository(private val api: PokemonApi, private val dao: PokemonDAO) : IPokemonRepository {

    var pagerPokemon: Pager<Int, PokemonEntity>? = null

    override var state: MutableLiveData<IPokemonRepository.State> = MutableLiveData(IPokemonRepository.State.DEFAULT)

    override suspend fun getPokemonList(): Flow<PagingData<PokemonEntity>>? {

        pagerPokemon = Pager(PagingConfig(pageSize = 10)) {
            PokemonDataSource(api, dao, state)
        }

        return pagerPokemon?.flow
    }
}