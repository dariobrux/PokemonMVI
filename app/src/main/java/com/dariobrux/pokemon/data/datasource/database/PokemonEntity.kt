package com.dariobrux.pokemon.data.datasource.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(

    @PrimaryKey
    @NonNull
    var name: String = ""
)
