package com.dariobrux.pokemon.di

import org.koin.dsl.module

val roomDatabaseModule = module {

//    // Weather Room Data Repository
//    single<WeatherEntityRepository>(override = true) { WeatherEntityPersistentRepositoryImpl(get(), get()) }
//    // Room Database
//    single {
//        Room.databaseBuilder(androidApplication(), WeatherDatabase::class.java, "weather-db")
//                .build()
//    }
//    // Expose WeatherDAO
//    single { get<WeatherDatabase>().weatherDAO() }
}
