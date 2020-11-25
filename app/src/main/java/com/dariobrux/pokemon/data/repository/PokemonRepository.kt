package com.dariobrux.pokemon.data.repository

import com.dariobrux.pokemon.data.datasource.database.PokemonDAO
import com.dariobrux.pokemon.data.datasource.database.PokemonEntity
import com.dariobrux.pokemon.data.datasource.webservice.PokemonDataSource
import com.dariobrux.pokemon.domain.model.Pokemon
import timber.log.Timber

class PokemonRepository(private val dataSource: PokemonDataSource, private val dao: PokemonDAO) : IPokemonRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int): List<Pokemon> {

        var pokemonList: List<Pokemon> = kotlin.runCatching {
            dao.getPokemonList(offset, limit)
        }.mapCatching {
            it?.run {
                Timber.tag(TAG).d("Pokemon retrieved from database.")
                map { entity ->
                    Pokemon(entity.name)
                }
            } ?: emptyList()
        }.onFailure {
            emptyList<Pokemon>()
        }.getOrElse {
            Timber.tag(TAG).d("No Pokemon in database.")
            emptyList()
        }

        if (pokemonList.isEmpty()) {

            Timber.tag(TAG).d("Trying to retrieve the pokemon list from webService.")

            kotlin.runCatching {
                dataSource.getPokemonListAsync(offset, limit).await()
            }.mapCatching {
                it.pokemonList
            }.onSuccess {
                it?.run {
                    Timber.tag(TAG).d("Pokemon retrieved from webService.")
                    pokemonList = it
                } ?: emptyList<Pokemon>()
            }.onFailure {
                Timber.tag(TAG).w("Problems while retrieve the pokemon list. Error message: $it")
                emptyList<Pokemon>()
            }

            pokemonList.map {
                PokemonEntity(it.name)
            }.let {
                kotlin.runCatching {
                    dao.insertPokemonList(it)
                }.onSuccess {
                    Timber.tag(TAG).d("Insert the pokemon list in the database.")
                }.onFailure {
                    Timber.tag(TAG).d("Failed inserting the pokemon list in the database.")
                }
            }
        }

        Timber.tag(TAG).d("Pokemon list: $pokemonList")

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