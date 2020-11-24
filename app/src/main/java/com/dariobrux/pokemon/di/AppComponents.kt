package com.dariobrux.pokemon.di

import com.dariobrux.pokemon.data.repository.IPokemonRepository
import com.dariobrux.pokemon.data.repository.PokemonRepository
import com.dariobrux.pokemon.domain.usecase.GetPokemonInfo
import com.dariobrux.pokemon.domain.usecase.GetPokemonList
import com.dariobrux.pokemon.domain.usecase.LoadPokemonInfo
import com.dariobrux.pokemon.domain.usecase.LoadPokemonList
import com.dariobrux.pokemon.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * App Components
 */
val appModule = module {
    // ViewModels
//    viewModel { (id: DailyForecastId) -> DetailViewModel(id, get()) }
    viewModel { SplashViewModel(get()) }
//    viewModel { WeatherListViewModel(get(), get()) }

    // Use cases
    factory { GetPokemonList(get()) }
    factory { GetPokemonInfo(get()) }
    factory { LoadPokemonList(get()) }
    factory { LoadPokemonInfo(get()) }

    // Data Repository
    single<IPokemonRepository> { PokemonRepository() }
}

// Gather all app modules
val onlinePokemonApp = appModule + remoteWebServiceModule
val offlinePokemonApp = onlinePokemonApp + mockWebServiceModule
val offlineDatabasePokemonApp = offlinePokemonApp + roomDatabaseModule