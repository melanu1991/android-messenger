package com.uandcode.messenger.features.init.domain

import com.uandcode.messenger.core.essentials.exceptions.AppException
import com.uandcode.messenger.core.essentials.exceptions.HasLocalizedMessage
import com.uandcode.messenger.core.essentials.resources.StringProviderStore

abstract class InitAppException(
    message: String,
    cause: Throwable? = null
): AppException(message, cause), HasLocalizedMessage {
    abstract fun getLocalizedErrorMessage(stringProviderStore: InitStringProvider): String

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<InitStringProvider>())
    }
}

class DeviceIsRootedException: InitAppException("Device is rooted") {
    override fun getLocalizedErrorMessage(stringProviderStore: InitStringProvider): String {
        return stringProviderStore.deviceIsRootedErrorMessage
    }
}