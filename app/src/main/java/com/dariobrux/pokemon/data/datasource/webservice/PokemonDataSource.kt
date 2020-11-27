package com.dariobrux.pokemon.data.datasource.webservice

import androidx.paging.PagingSource
import com.dariobrux.pokemon.common.getLimitParameter
import com.dariobrux.pokemon.common.getOffsetParameter
import com.dariobrux.pokemon.common.toPokemonEntity
import com.dariobrux.pokemon.common.toPokemonEntityList
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class PokemonDataSource(private val api: PokemonApi) : PagingSource<Int, PokemonEntity>() {

    @ExperimentalCoroutinesApi
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return try {
            val nextPageNumber = params.key ?: 0
            val rootData = api.getPokemonListAsync(nextPageNumber).await()

            val pokemonList = rootData.results!!.toPokemonEntityList().toMutableList()
            val previous = rootData.previous.getOffsetParameter()
            val next = rootData.next.getOffsetParameter()
            val limit = rootData.previous.getLimitParameter()

            Timber.tag(TAG).d("Retrieve Pokemon from offset $next to ${(next ?: 0) + (limit ?: 0)}")

            rootData.results?.forEachIndexed { index, value ->
                value.url?.run {
                    api.getPokemonInfoAsync(this)
                }?.await()?.let {
                    pokemonList[index] = it.toPokemonEntity()
                }
            }

            LoadResult.Page(
                data = pokemonList,
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