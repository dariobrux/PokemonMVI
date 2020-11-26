package com.dariobrux.pokemon.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dariobrux.pokemon.data.datasource.database.converter.StatsConverter
import com.dariobrux.pokemon.data.datasource.database.converter.TypeConverter
import com.dariobrux.pokemon.data.datasource.database.model.PokemonEntity
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.dariobrux.pokemon.data.datasource.database.model.TypeEntity

@Database(
    entities = [
        PokemonEntity::class,
        StatsEntity::class,
        TypeEntity::class],
    version = 1
)
@TypeConverters(TypeConverter::class, StatsConverter::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO
}