package com.dariobrux.pokemon

import android.app.Application
import io.uniflow.androidx.logger.AndroidMessageLogger
import io.uniflow.core.logger.UniFlowLogger

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        UniFlowLogger.init(AndroidMessageLogger(UniFlowLogger.FUN_TAG, true))
    }
}