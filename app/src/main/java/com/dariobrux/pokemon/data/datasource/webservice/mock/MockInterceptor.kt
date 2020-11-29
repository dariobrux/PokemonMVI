package com.dariobrux.pokemon.data.datasource.webservice.mock

import com.dariobrux.pokemon.common.file.JsonReader
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import java.util.*

class MockInterceptor(private val reader: JsonReader) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.toString()
        val file = extractUrlToFile(url)
        val content = reader.getFileContent("$file.json")
        return Response.Builder()
            .code(200)
//            .body(ResponseBody.create(MediaType.parse("application/json"), content))
            .body(content.toResponseBody("application/json".toMediaTypeOrNull()))
            .request(chain.request())
            .message(content)
            .protocol(Protocol.HTTP_1_1)
            .build()
    }
}

fun extractUrlToFile(url: String): String {
    val names = url.split("/").toMutableList()
    names.remove("")
    names.remove("https:")
    names.remove("http:")
    val filtered = names.map {
        it.replace("?", "_").replace("=", "_")
    }
    return filtered.last().toLowerCase(Locale.getDefault())
}
