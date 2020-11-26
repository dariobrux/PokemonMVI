package com.dariobrux.pokemon.data.datasource.database.converter

import androidx.room.TypeConverter
import com.dariobrux.pokemon.data.datasource.database.model.TypeEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class TypeConverter {

    @TypeConverter
    fun fromTypeList(list: List<TypeEntity>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<TypeEntity>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toTypeList(typeStr: String): List<TypeEntity>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<TypeEntity>>() {}.type
        return gson.fromJson<List<TypeEntity>>(typeStr, type)
    }
}