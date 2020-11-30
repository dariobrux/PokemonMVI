package com.dariobrux.pokemon.data.datasource.webservice

import com.dariobrux.pokemon.domain.model.root.RootData
import com.dariobrux.pokemon.domain.model.info.PokemonInfo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 *
 * Created by Dario Bruzzese on 26/11/2020.
 *
 */
interface PokemonApi {

    /**
     * Get the [RootData] with the data to download.
     * @param offset it will be retrieved a list starting from this value
     * @param limit maximum number of items to retrieve. Default 20.
     * @return the [RootData] mapped into an async response.
     */
    @GET("api/v2/pokemon")
    fun getPokemonListAsync(@Query("offset") offset: Int, @Query("limit") limit: Int = 20): Deferred<RootData>

    /**
     * Get the [PokemonInfo] containing info of a single Pokemon.
     * @param url the url to call to get the info.
     * @return the [PokemonInfo] object mapped into an async response.
     */
    @GET
    fun getPokemonInfoAsync(@Url url: String): Deferred<PokemonInfo>

}