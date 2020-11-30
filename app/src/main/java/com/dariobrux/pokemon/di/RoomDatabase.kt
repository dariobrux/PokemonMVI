package com.dariobrux.pokemon.di

import androidx.room.Room
import com.dariobrux.pokemon.data.datasource.database.PokemonDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 *
 * Created by Dario Bruzzese on 24/11/2020.
 *
 * This file is one of the Koin Dependency Injection creator objects in this project.
 * It creates the dependency injection for the database module, declaring Room database and DAO.
 *
 */

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), PokemonDatabase::class.java, "pokemon-db")
            .build()
    }
    single { get<PokemonDatabase>().pokemonDAO() }
}
