package com.uandcode.messenger.common.android

import com.uandcode.messenger.core.essentials.logger.Logger
import timber.log.Timber

class AndroidLogger: Logger {
    override fun d(message: String) {
        Timber.d(message)
    }

    override fun e(exception: Exception, message: String) {
        Timber.e(exception)
    }
}