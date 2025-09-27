package com.uandcode.messenger.core.essentials.exceptions

import com.uandcode.messenger.core.essentials.resources.CoreStringProvider
import com.uandcode.messenger.core.essentials.resources.StringProviderStore
import jdk.internal.joptsimple.internal.Messages.message

abstract class AppException(
    message: String,
    cause: Throwable? = null
): Exception(message, cause)

abstract class CoreAppException(
    message: String,
    cause: Throwable? = null
): AppException(message, cause), HasLocalizedMessage {
    abstract fun getLocalizedErrorMessage(stringProviderStore: CoreStringProvider): String

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<CoreStringProvider>())
    }
}

class UnknownException(
    message: String = "Unknown error"
): AppException(message)

class ConnectionException(
    cause: Throwable? = null
): CoreAppException("Connection error", cause), HasLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: CoreStringProvider): String {
        return stringProviderStore.connectionErrorMessage
    }
}

class BackendException(
    val httpCode: Int = 400,
    val backendMessage: String = "",
    cause: Throwable? = null
): CoreAppException("Server error", cause) {
    override fun getLocalizedErrorMessage(stringProviderStore: CoreStringProvider): String {
        return stringProviderStore.backendErrorMessage(httpCode, backendMessage)
    }
}