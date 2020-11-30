package com.dariobrux.pokemon.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import com.dariobrux.pokemon.common.extension.getLimitParameter
import com.dariobrux.pokemon.common.extension.getOffsetParameter
import com.dariobrux.pokemon.common.extension.toPokemonEntity
import com.dariobrux.pokemon.common.extension.toPokemonEntityList
import com.dariobrux.pokemon.data.datasource.database.PokemonDAO
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.datasource.webservice.PokemonApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

class PokemonDataSource(private val api: PokemonApi, private val dao: PokemonDAO, private val state: MutableLiveData<IPokemonRepository.State>) : PagingSource<Int, PokemonEntity>() {

    @ExperimentalCoroutinesApi
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return try {
            state.value = IPokemonRepository.State.LOADING

            var limit: Int? = 20
            val nextPageNumber = params.key ?: 0

            var pokemonEntityList = dao.getPokemonList(nextPageNumber)
            if (!pokemonEntityList.isNullOrEmpty()) {
                Timber.tag(TAG).d("Pokemon retrieved from Database.")
            }

            var previous: Int? = if (nextPageNumber == 0) null else nextPageNumber - (limit ?: 0)
            var next: Int? = (limit ?: 0) + nextPageNumber

            if (pokemonEntityList.isNullOrEmpty()) {

                val rootData = api.getPokemonListAsync(nextPageNumber).await()
                pokemonEntityList = rootData.results!!.toPokemonEntityList().toMutableList()

                previous = rootData.previous.getOffsetParameter()
                next = rootData.next.getOffsetParameter()
                limit = rootData.previous.getLimitParameter()

                Timber.tag(TAG).d("Retrieve Pokemon from offset $next to ${(next ?: 0) + (limit ?: 0)}")

                rootData.results?.forEachIndexed { index, value ->
                    value.url?.run {
                        api.getPokemonInfoAsync(this)
                    }?.await()?.let {
                        val pokemonEntity = it.toPokemonEntity()
                        pokemonEntityList[index] = pokemonEntity

                        Timber.tag(TAG).d("Storing ${pokemonEntity.name}...")
                        dao.insertPokemon(pokemonEntity)
                    }
                }
            }
            state.value = IPokemonRepository.State.LOADED

            LoadResult.Page(
                data = pokemonEntityList,
                prevKey = previous,
                nextKey = next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val TAG = "PokemonDataSource"
    }
}