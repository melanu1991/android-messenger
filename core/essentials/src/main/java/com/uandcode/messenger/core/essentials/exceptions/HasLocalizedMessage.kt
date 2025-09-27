package com.uandcode.messenger.core.essentials.exceptions

import com.uandcode.messenger.core.essentials.resources.StringProviderStore

interface HasLocalizedMessage {
    fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String
}