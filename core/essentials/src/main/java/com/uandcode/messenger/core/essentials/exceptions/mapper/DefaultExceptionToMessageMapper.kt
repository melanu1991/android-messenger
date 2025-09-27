package com.uandcode.messenger.core.essentials.exceptions.mapper

import com.uandcode.messenger.core.essentials.exceptions.HasLocalizedMessage
import com.uandcode.messenger.core.essentials.resources.CoreStringProvider
import com.uandcode.messenger.core.essentials.resources.StringProviderStore
import javax.inject.Inject

class DefaultExceptionToMessageMapper @Inject constructor(
    private val stringProviderStore: StringProviderStore
): ExceptionToMessageMapper {
    override fun getLocalizedMessage(exception: Exception): String {
        return if (exception is HasLocalizedMessage) {
            exception.getLocalizedErrorMessage(stringProviderStore)
        } else {
            stringProviderStore<CoreStringProvider>().unknownErrorMessage
        }
    }
}