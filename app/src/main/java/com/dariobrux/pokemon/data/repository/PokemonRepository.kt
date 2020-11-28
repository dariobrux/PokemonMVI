package com.dariobrux.pokemon.data.repository

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

    override suspend fun getPokemonList(offset: Int, limit: Int): Flow<PagingData<PokemonEntity>>? {

        pagerPokemon = Pager(PagingConfig(pageSize = 10)) {

//            var pokemonList: List<PokemonEntity> = emptyList()

//            kotlin.runCatching {
//                dao.getPokemonList(offset, limit)
//            }.onSuccess {
//                if (!it.isNullOrEmpty()) {
//                    Timber.tag(TAG).d("Pokemon retrieved from Database.")
//                    pokemonList = it
//                }
//            }.onFailure {
//                Timber.tag(TAG).w("Problems while retrieving Pokemon list from Database. Error message: $it")
//            }

//            if (pokemonList.isEmpty()) {
//                kotlin.runCatching {

//                api.getPokemonListAsync(offset, limit).await().results?.let {
//                    Timber.tag(TAG).d("Pokemon retrieved from WebService.")
//                    it.forEach { pokemon ->
//                        Timber.tag(TAG).d("Storing ${pokemon.name}...")
//                        val pokemonInfo = api.getPokemonInfoAsync(pokemon.url ?: "").await()
//                        dao.insertPokemon(pokemonInfo.toPokemonEntity())
//                    }
//                }
//                }.onFailure {
//                    Timber.tag(TAG).w("Problems while retrieving Pokemon list from WebService. Error message: $it")
//                }
//            }

            PokemonDataSource(api, dao)
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