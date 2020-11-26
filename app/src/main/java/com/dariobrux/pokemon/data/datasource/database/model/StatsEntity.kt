package com.dariobrux.pokemon.data.datasource.database.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
data class StatsEntity(

    @PrimaryKey
    @NonNull
    var name: String = "",

    @NonNull
    var value: Double = 0.0

)