package com.uandcode.messenger.common.android

import android.app.Application
import com.uandcode.messenger.core.essentials.logger.Logger
import timber.log.Timber

abstract class AbstractApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Logger.init(AndroidLogger())
    }
}