package com.dariobrux.pokemon.common.file

import android.app.Application
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Read Json File from assets/json
 */
class AndroidReader(val application: Application) : JsonReader {

    override fun getFileContent(fileName: String): String {
        println("AndroidReader -> $fileName")
        val buf = StringBuilder()
        val json = application.assets.open("json/${fileName.toLowerCase(Locale.getDefault())}")
        BufferedReader(InputStreamReader(json, "UTF-8"))
            .use {
                val list = it.lineSequence().toList()
                buf.append(list.joinToString("\n"))
            }

        return buf.toString()
    }
}