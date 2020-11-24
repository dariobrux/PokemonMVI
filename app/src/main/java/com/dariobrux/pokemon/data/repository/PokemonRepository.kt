package com.dariobrux.pokemon.data.repository

import com.dariobrux.pokemon.domain.model.Pokemon

/**
 * Weather repository
 * Make use of WeatherDataSource & add some cache
 */
class PokemonRepository(/*private val weatherDatasource: WeatherDataSource*/) : IPokemonRepository {

    override suspend fun getPokemonList(location: String?): List<Pokemon> {
        //TODO("Not yet implemented")
        return listOf()
    }

    override suspend fun getPokemonDetail(id: String): Pokemon {
        //TODO("Not yet implemented")
        return Pokemon("Ciao")
    }
}
//    WeatherEntityRepository {
//
//    private val weatherCache = arrayListOf<WeatherEntity>()
//
//    private fun lastLocationFromCache() = weatherCache.firstOrNull()?.location
//
//    override suspend fun getWeatherDetail(
//        id: DailyForecastId): DailyForecast = weatherCache.first { it.id == id.value }.mapToDailyForecast()
//
//    override suspend fun getWeather(
//        location: String?
//    ): List<DailyForecast> {
//        // Take cache
//        return if (isAlreadyInCache(location)) weatherCache.mapToDailyForecasts()
//        else {
//            weatherCache.clear()
//
//            val targetLocation: String = location ?: lastLocationFromCache() ?: DEFAULT_LOCATION
//
//            val (lat, lng) = weatherDatasource.geocode(targetLocation).await().mapToLocation() ?: error(
//                "Can't map to location: '$targetLocation'")
//
//            val weather: List<WeatherEntity> = weatherDatasource.weather(lat, lng,
//                DEFAULT_LANG).await().mapToWeatherEntities(targetLocation)
//
//            weatherCache.addAll(weather)
//            weather.mapToDailyForecasts()
//        }
//    }
//
//    private fun isAlreadyInCache(location: String?) = location == null && weatherCache.isNotEmpty()