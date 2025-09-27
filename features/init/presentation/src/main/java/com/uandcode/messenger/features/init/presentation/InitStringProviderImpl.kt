package com.uandcode.messenger.features.init.presentation

import android.content.Context
import com.uandcode.messenger.core.essentials.resources.StringProvider
import com.uandcode.messenger.features.init.domain.InitStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InitStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : InitStringProvider {
    override val deviceIsRootedErrorMessage: String = context.getString(R.string.rooted_device_error)
}

