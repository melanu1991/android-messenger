package com.uandcode.messenger.core.essentials.logger

import java.lang.Exception

interface Logger {
    fun d(message: String)
    fun e(exception: Exception, message: String = "Error!")

    companion object: Logger {
        private var logger: Logger = DefaultLogger()

        fun init(logger: Logger) {
            this.logger = logger
        }

        override fun d(message: String) {
            logger.d(message)
        }

        override fun e(exception: Exception, message: String) {
            logger.e(exception, message)
        }
    }
}