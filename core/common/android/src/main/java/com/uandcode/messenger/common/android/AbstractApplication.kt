package com.uandcode.messenger.common.android

import android.app.Application
import com.uandcode.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.uandcode.messenger.core.essentials.logger.Logger
import timber.log.Timber
import javax.inject.Inject

abstract class AbstractApplication: Application() {
    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var exceptionToMessageMapper: ExceptionToMessageMapper

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Logger.init(logger)
        ExceptionToMessageMapper.init(exceptionToMessageMapper)
    }
}