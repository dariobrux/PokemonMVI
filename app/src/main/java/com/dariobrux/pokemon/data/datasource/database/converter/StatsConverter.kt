package com.dariobrux.pokemon.data.datasource.database.converter

import androidx.room.TypeConverter
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


class StatsConverter {

    private val moshi = Moshi.Builder().build()
    private val type: Type = Types.newParameterizedType(MutableList::class.java, StatsEntity::class.java)

    @TypeConverter
    fun toJson(list: List<StatsEntity>): String {
        val adapter = moshi.adapter<List<StatsEntity>>(type)
        return adapter.toJson(list)
    }

    @TypeConverter
    fun fromJson(str: String): List<StatsEntity> {
        val adapter = moshi.adapter<List<StatsEntity>>(type)
        return adapter.fromJson(str) ?: listOf()
    }
}