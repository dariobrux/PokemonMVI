package com.dariobrux.pokemon.common.extension

import com.dariobrux.pokemon.data.datasource.database.model.ImageEntity
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.dariobrux.pokemon.data.datasource.database.model.TypeEntity
import com.dariobrux.pokemon.domain.model.info.PokemonInfo
import com.dariobrux.pokemon.domain.model.info.Sprite
import com.dariobrux.pokemon.domain.model.info.Stats
import com.dariobrux.pokemon.domain.model.info.Types
import com.dariobrux.pokemon.domain.model.root.Results

/**
 *
 * Created by Dario Bruzzese on 25/11/2020.
 *
 * This file contains all the extended methods for Pokemon related objects.
 *
 */

/**
 * Convert a [PokemonInfo] object to a [PokemonEntity] object.
 * @return the [PokemonEntity] object.
 */
fun PokemonInfo.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        name = this.name ?: "",
        images = this.sprites.toImageEntityList(),
        types = this.types.toTypeEntityList(),
        stats = this.stats.toStatsEntityList(),
        id = this.id ?: 0,
        baseExperience = this.baseExperience ?: 0
    )
}

/**
 * Convert a list of [Results] object to a list of [PokemonEntity] object.
 * @return the list of [PokemonEntity] object.
 */
fun List<Results>.toPokemonEntityList(): List<PokemonEntity> {
    return this.map {
        PokemonEntity(name = it.name ?: "")
    }
}

/**
 * Convert a [Sprite] to a ist of [ImageEntity] object.
 * @return the list of [ImageEntity] object.
 */
fun Sprite?.toImageEntityList(): List<ImageEntity> {
    return listOfNotNull(
        this?.other?.officialArtwork?.frontDefault,
        this?.other?.dreamWorld?.frontDefault,
        this?.other?.dreamWorld?.frontFemale,
        this?.frontDefault,
        this?.frontFemale,
        this?.backDefault,
        this?.backFemale,
        this?.frontShiny,
        this?.frontShinyFemale,
        this?.backShiny,
        this?.backShinyFemale,
    ).map {
        ImageEntity(it)
    }
}

/**
 * Convert a list of [Types] object to a ist of [TypeEntity] object.
 * @return the list of [TypeEntity] object.
 */
fun List<Types>?.toTypeEntityList(): List<TypeEntity> {
    return this?.map {
        TypeEntity(name = it.type?.name ?: "")
    } ?: emptyList()
}

/**
 * Convert a list of [Stats] object to a ist of [StatsEntity] object.
 * @return the list of [StatsEntity] object.
 */
fun List<Stats>?.toStatsEntityList(): List<StatsEntity> {
    return this?.map {
        StatsEntity(name = it.stat?.name ?: "", value = it.baseStat ?: 0)
    } ?: emptyList()
}