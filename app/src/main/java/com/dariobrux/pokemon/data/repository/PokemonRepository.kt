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

    override var state: MutableLiveData<State> = MutableLiveData(State.DEFAULT)

    enum class State {
        DEFAULT,
        LOADING,
        LOADED
    }

    override suspend fun getPokemonList(offset: Int, limit: Int): Flow<PagingData<PokemonEntity>>? {

        pagerPokemon = Pager(PagingConfig(pageSize = 10)) {
            PokemonDataSource(api, dao, state)
        }
        return pagerPokemon?.flow
    }

    override suspend fun getPokemonDetail(id: String): Pokemon {
        //TODO("Not yet implemented")
        return Pokemon("Ciao")
    }

    companion object {
        private const val TAG = "PokemonRepository"
    }
}