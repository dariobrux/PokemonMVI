package com.dariobrux.pokemon.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity

/**
 *
 * Created by Dario Bruzzese on 21/10/2020.
 *
 * This interface is the DAO.
 * It's responsible for defining the methods that access the database.
 *
 */

@Dao
interface PokemonDAO {

    /**
     * Get the list of all the pokemon stored.
     * @return the list with all pokemon.
     */
    @Query("Select * from pokemon limit :limit offset :offset")
    fun getPokemonList(offset: Int, limit: Int): List<PokemonEntity>?

//    /**
//     * Get the info of a specific pokemon.
//     * @param name the name of the pokemon.
//     * @return the [PokemonData] object with the pokemon info.
//     */
//    @Query("Select * from pokemonData where name like :name")
//    fun getPokemonData(name: String) : PokemonData?

    /**
     * Add a list of pokemon in the database.
     * @param pokemonList: the [Pokemon] list to insert.
     * Use the replacing strategy to override each existing item.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonList(pokemonList: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: PokemonEntity)

//    /**
//     * Add a pokemon in the database.
//     * @param pokemonData: the [PokemonData] to insert.
//     * Use the replacing strategy to override each existing item.
//     */
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertPokemonData(pokemonData: PokemonData)
}