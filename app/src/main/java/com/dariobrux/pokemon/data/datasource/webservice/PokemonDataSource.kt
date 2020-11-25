package com.dariobrux.pokemon.data.datasource.webservice

import com.dariobrux.pokemon.domain.model.DataInfo
import com.dariobrux.pokemon.domain.model.Pokemon
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Weather datasource - Retrofit tagged
 */
interface PokemonDataSource {

    /**
     * Get the [DataInfo] with the pokemon list.
     * @param offset it will be retrieved a list starting from this value
     * @param limit maximum number of items to retrieve.
     * @return the [DataInfo] mapped into a [Response] object.
     */
    @GET("api/v2/pokemon")
    fun getPokemonListAsync(@Query("offset") offset: Int, @Query("limit") limit: Int): Deferred<DataInfo>

}