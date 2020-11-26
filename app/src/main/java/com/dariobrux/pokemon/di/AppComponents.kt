package com.dariobrux.pokemon.di

import com.dariobrux.pokemon.data.repository.IPokemonRepository
import com.dariobrux.pokemon.data.repository.PokemonRepository
import com.dariobrux.pokemon.domain.usecase.GetPokemonInfo
import com.dariobrux.pokemon.domain.usecase.GetPokemonList
import com.dariobrux.pokemon.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * App Components
 */
val appModule = module {
    // ViewModels
//    viewModel { (id: DailyForecastId) -> DetailViewModel(id, get()) }
    viewModel { MainViewModel(get()) }
//    viewModel { WeatherListViewModel(get(), get()) }

    // Use cases
    factory { GetPokemonList(get()) }
    factory { GetPokemonInfo(get()) }
//    factory { LoadPokemonList(get()) }
//    factory { LoadPokemonInfo(get()) }

    // Data Repository
    single<IPokemonRepository> { PokemonRepository(get(), get()) }
}

// Gather all app modules
val onlinePokemonApp = appModule + remoteWebServiceModule
val offlinePokemonApp = onlinePokemonApp + mockWebServiceModule
val offlineDatabasePokemonApp = offlinePokemonApp + roomDatabaseModule