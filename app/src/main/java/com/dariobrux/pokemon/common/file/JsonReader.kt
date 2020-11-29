package com.dariobrux.pokemon.common.file

/**
 * Json reader
 */
interface JsonReader {
    fun getFileContent(fileName: String): String
}