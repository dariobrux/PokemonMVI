package com.dariobrux.pokemon.di

import com.dariobrux.pokemon.common.file.AndroidReader
import com.dariobrux.pokemon.common.file.JsonReader
import com.dariobrux.pokemon.data.datasource.webservice.mock.MockInterceptor
import org.koin.dsl.module

/**
 * Local Json Files Datasource
 */
val mockWebServiceModule = module(override = true) {
    // provided web components
//    single { createOkHttpClient(get()) }
//    single { MockInterceptor(get()) }
//    single<JsonReader> { AndroidReader(get()) }
}