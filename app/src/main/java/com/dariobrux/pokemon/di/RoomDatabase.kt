package com.dariobrux.pokemon.di

import androidx.room.Room
import com.dariobrux.pokemon.data.datasource.database.PokemonDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDatabaseModule = module {

//    // Pokemon Room Data Repository
//    single<IPokemonRepository>(override = true) { PokemonPersistentRepository(get(), get()) }

    // Room Database
    single {
        Room.databaseBuilder(androidApplication(), PokemonDatabase::class.java, "pokemon-db")
            .build()
    }

    // Expose PokemonDAO
    single { get<PokemonDatabase>().pokemonDAO() }
}
