package com.uandcode.messenger.features.init.domain

import com.uandcode.messenger.core.essentials.resources.StringProvider

interface InitStringProvider: StringProvider {
    val deviceIsRootedErrorMessage: String
}

