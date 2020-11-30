package com.dariobrux.pokemon

import android.app.Application
import com.dariobrux.pokemon.di.offlineDatabasePokemonApp
import com.dariobrux.pokemon.di.onlinePokemonApp
import io.uniflow.androidx.logger.AndroidMessageLogger
import io.uniflow.core.logger.UniFlowLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

/**
 *
 * Created by Dario Bruzzese on 24/11/2020.
 *
 * This is the application, where init Dependency Injection and other
 * useful libraries.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            androidFileProperties()
            modules(offlineDatabasePokemonApp)
        }

        UniFlowLogger.init(AndroidMessageLogger(UniFlowLogger.FUN_TAG, true))

        Timber.plant(Timber.DebugTree())
    }
}