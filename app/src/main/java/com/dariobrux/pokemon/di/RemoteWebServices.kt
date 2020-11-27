package com.dariobrux.pokemon.di

import com.dariobrux.pokemon.data.datasource.webservice.PokemonApi
import com.dariobrux.pokemon.data.datasource.webservice.mock.MockInterceptor
import com.dariobrux.pokemon.di.Properties.SERVER_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Remote Web Service datasource
 */
val remoteWebServiceModule = module {
    // provided web components
    single { createOkHttpClient() }
    // Fill property
    single { createWebService<PokemonApi>(get(), getProperty(SERVER_URL)) }
}

object Properties {
    const val SERVER_URL = "SERVER_URL"
}

fun createOkHttpClient(mockInterceptor: MockInterceptor? = null): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
    val builder = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
    return if (mockInterceptor != null) builder.addInterceptor(mockInterceptor).build() else builder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    return retrofit.create(T::class.java)
}