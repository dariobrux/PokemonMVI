package com.dariobrux.pokemon.data.datasource.database.converter

import androidx.room.TypeConverter
import com.dariobrux.pokemon.data.datasource.database.model.StatsEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class StatsConverter {

    @TypeConverter
    fun fromTypeList(list: List<StatsEntity>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<StatsEntity>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toTypeList(str: String): List<StatsEntity>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<StatsEntity>>() {}.type
        return gson.fromJson<List<StatsEntity>>(str, type)
    }
}