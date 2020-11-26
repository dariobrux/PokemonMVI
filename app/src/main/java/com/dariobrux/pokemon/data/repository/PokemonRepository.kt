package com.dariobrux.pokemon.data.repository

import com.dariobrux.pokemon.common.toPokemonEntity
import com.dariobrux.pokemon.data.datasource.database.PokemonDAO
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.datasource.webservice.PokemonDataSource
import com.dariobrux.pokemon.domain.model.Pokemon
import timber.log.Timber

class PokemonRepository(private val dataSource: PokemonDataSource, private val dao: PokemonDAO) : IPokemonRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int): List<PokemonEntity> {

        var pokemonList: List<PokemonEntity> = emptyList()

        kotlin.runCatching {
            dao.getPokemonList(offset, limit)
        }.onSuccess {
            if (!it.isNullOrEmpty()) {
                Timber.tag(TAG).d("Pokemon retrieved from Database.")
                pokemonList = it
            }
        }.onFailure {
            Timber.tag(TAG).w("Problems while retrieving Pokemon list from Database. Error message: $it")
        }

        if (pokemonList.isEmpty()) {
            kotlin.runCatching {
                dataSource.getPokemonListAsync(offset, limit).await().results?.let {
                    Timber.tag(TAG).d("Pokemon retrieved from WebService.")
                    it.forEach { pokemon ->
                        Timber.tag(TAG).d("Storing ${pokemon.name}...")
                        val pokemonInfo = dataSource.getPokemonInfoAsync(pokemon.url ?: "").await()
                        dao.insertPokemon(pokemonInfo.toPokemonEntity())
                    }
                }
            }.onFailure {
                Timber.tag(TAG).w("Problems while retrieving Pokemon list from WebService. Error message: $it")
            }
        }
        return pokemonList
    }

    override suspend fun getPokemonDetail(id: String): Pokemon {
        //TODO("Not yet implemented")
        return Pokemon("Ciao")
    }

    companion object {
        private const val TAG = "PokemonRepository"
    }
}