package com.dariobrux.pokemon.di

import com.dariobrux.pokemon.data.repository.IPokemonRepository
import com.dariobrux.pokemon.data.repository.PokemonRepository
import com.dariobrux.pokemon.domain.usecase.GetPokemonList
import com.dariobrux.pokemon.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 * Created by Dario Bruzzese on 24/11/2020.
 *
 * This file is one of the Koin Dependency Injection creator objects in this project.
 * It creates the dependency injection for the application module, especially for the
 * ViewModel, the Use Cases and the Repository.
 *
 */

val appModule = module {

    viewModel { MainViewModel(get()) }

    factory { GetPokemonList(get()) }

    single<IPokemonRepository> { PokemonRepository(get(), get()) }
}

val onlinePokemonApp = appModule + remoteWebServiceModule
val offlineDatabasePokemonApp = onlinePokemonApp + roomDatabaseModule