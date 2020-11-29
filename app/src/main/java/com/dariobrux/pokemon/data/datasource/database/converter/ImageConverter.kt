package com.dariobrux.pokemon.data.datasource.database.converter

import androidx.room.TypeConverter
import com.dariobrux.pokemon.data.datasource.database.model.ImageEntity
import com.dariobrux.pokemon.data.datasource.database.model.TypeEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


class ImageConverter {

    private val moshi = Moshi.Builder().build()
    private val type: Type = Types.newParameterizedType(MutableList::class.java, ImageEntity::class.java)

    @TypeConverter
    fun toJson(list: List<ImageEntity>): String {
        val adapter = moshi.adapter<List<ImageEntity>>(type)
        return adapter.toJson(list)
    }

    @TypeConverter
    fun fromJson(str: String): List<ImageEntity> {
        val adapter = moshi.adapter<List<ImageEntity>>(type)
        return adapter.fromJson(str) ?: listOf()
    }
}