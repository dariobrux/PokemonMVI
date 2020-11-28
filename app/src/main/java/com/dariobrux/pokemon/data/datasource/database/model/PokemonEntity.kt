package com.dariobrux.pokemon.data.datasource.database.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "pokemon")
data class PokemonEntity(

    @PrimaryKey
    @NonNull
    var name: String = "",

    @NonNull
    var image: String = "",

    @NonNull
    var types: List<TypeEntity> = listOf(),

    @NonNull
    var stats: List<StatsEntity> = listOf(),

    @NonNull
    var id: Int = 0

) : Serializable
