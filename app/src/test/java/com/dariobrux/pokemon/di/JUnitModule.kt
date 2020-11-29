//package com.dariobrux.pokemon.di
//
//import com.dariobrux.pokemon.common.file.JavaReader
//import com.dariobrux.pokemon.common.file.JsonReader
//import org.koin.dsl.module
//
//
///**
// * Local java json getCurrentWeather
// */
//val JUnitMockModule = module(override = true) {
//    single<JsonReader> { JavaReader() }
//}
//
//val testWeatherApp = offlinePokemonApp + JUnitMockModule