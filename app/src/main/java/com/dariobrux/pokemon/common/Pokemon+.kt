package com.dariobrux.pokemon.common

import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.dariobrux.pokemon.data.datasource.database.model.TypeEntity
import com.dariobrux.pokemon.domain.model.info.PokemonInfo
import com.dariobrux.pokemon.domain.model.root.Results

fun PokemonInfo.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        name = this.name ?: "",
        image = this.sprites?.other?.officialArtwork?.frontDefault ?: "",
        types = this.types?.map {
            TypeEntity(name = it.type?.name ?: "")
        } ?: emptyList(),
        stats = this.stats?.map {
            StatsEntity(name = it.stat?.name ?: "", value = it.baseStat ?: 0)
        } ?: emptyList(),
        id = this.id ?: 0,
        baseExperience = this.baseExperience ?: 0
    )
}

fun List<Results>.toPokemonEntityList(): List<PokemonEntity> {
    return this.map {
        PokemonEntity(name = it.name ?: "")
    }
}