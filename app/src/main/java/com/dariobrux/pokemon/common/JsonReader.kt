package com.dariobrux.pokemon.common

/**
 * Json reader
 */
interface JsonReader {
    fun getFileContent(fileName: String): String
}