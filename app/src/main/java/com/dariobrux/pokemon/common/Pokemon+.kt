package com.dariobrux.pokemon.common

import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.dariobrux.pokemon.data.datasource.database.model.TypeEntity
import com.dariobrux.pokemon.domain.model.Pokemon
import com.dariobrux.pokemon.domain.model.PokemonInfo

fun List<PokemonEntity>?.toPokemonList(): List<Pokemon> {
    return this?.map {
        Pokemon(it.name)
    } ?: emptyList()
}

fun List<Pokemon>?.toPokemonEntityList(): List<PokemonEntity> {
    return this?.map {
        PokemonEntity(it.name)
    } ?: emptyList()
}

fun PokemonInfo.toPokemonEntity(): PokemonEntity {
    val types = mutableListOf<TypeEntity>()
    val stats = mutableListOf<StatsEntity>()

    this.types.forEach {
        val typeName = (it.getOrElse("type", { HashMap<Any, Any>() }) as Map<*, *>).getOrDefault("name", "").toString()
        types.add(TypeEntity(typeName))
    }

    this.stats.forEach {
        val baseStat = it.getOrElse("base_stat", { 0 }) as Double
        val name = (it.getOrElse("stat", { HashMap<Any, Any>() }) as Map<*, *>).values.firstOrNull().toString()
        stats.add(StatsEntity(name = name, value = baseStat))
    }

    return PokemonEntity(
        name = this.name,
        image = "", // todo
        types = types,
        stats = stats
    )
}