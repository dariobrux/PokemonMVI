package com.dariobrux.pokemon.di

import com.dariobrux.pokemon.data.datasource.webservice.PokemonApi
import com.dariobrux.pokemon.di.Properties.SERVER_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 *
 * Created by Dario Bruzzese on 24/11/2020.
 *
 * This file is one of the Koin Dependency Injection creator objects in this project.
 * It creates the dependency injection for the remote module, declaring Retrofit and OkHttp.
 *
 */

val remoteWebServiceModule = module {

    single { createOkHttpClient() }
    single { createWebService<PokemonApi>(get(), getProperty(SERVER_URL)) }
}

object Properties {
    const val SERVER_URL = "SERVER_URL"
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
    val builder = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
    return builder.build()
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