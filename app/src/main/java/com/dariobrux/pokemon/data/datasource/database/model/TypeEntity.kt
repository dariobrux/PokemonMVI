package com.dariobrux.pokemon.data.datasource.database.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "type")
data class TypeEntity(

    @PrimaryKey
    @NonNull
    var name: String = ""

) : Serializable