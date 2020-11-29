package com.dariobrux.pokemon.common

import com.dariobrux.pokemon.data.datasource.database.model.ImageEntity
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.dariobrux.pokemon.data.datasource.database.model.TypeEntity
import com.dariobrux.pokemon.domain.model.info.PokemonInfo
import com.dariobrux.pokemon.domain.model.info.Sprite
import com.dariobrux.pokemon.domain.model.info.Stats
import com.dariobrux.pokemon.domain.model.info.Types
import com.dariobrux.pokemon.domain.model.root.Results

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

fun List<Results>.toPokemonEntityList(): List<PokemonEntity> {
    return this.map {
        PokemonEntity(name = it.name ?: "")
    }
}

private fun Sprite?.toImageEntityList(): List<ImageEntity> {
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

private fun List<Types>?.toTypeEntityList(): List<TypeEntity> {
    return this?.map {
        TypeEntity(name = it.type?.name ?: "")
    } ?: emptyList()
}

private fun List<Stats>?.toStatsEntityList(): List<StatsEntity> {
    return this?.map {
        StatsEntity(name = it.stat?.name ?: "", value = it.baseStat ?: 0)
    } ?: emptyList()
}