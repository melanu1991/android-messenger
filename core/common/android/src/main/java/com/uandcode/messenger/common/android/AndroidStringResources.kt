package com.uandcode.messenger.common.android

import android.content.Context
import com.uandcode.messenger.core.essentials.resources.CoreStringProvider
import com.uandcode.messenger.core.essentials.resources.StringId
import com.uandcode.messenger.core.essentials.resources.StringResources
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidStringResources @Inject constructor(
    @ApplicationContext private val context: Context
): StringResources {
    override fun getString(resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }
}

class StringResourcesV2Impl @Inject constructor(
    @ApplicationContext private val context: Context
): com.uandcode.messenger.core.essentials.resources.StringResourcesV2 {
    override fun getString(stringId: com.uandcode.messenger.core.essentials.resources.StringId, vararg args: Any): String {
        return when (stringId) {
            is StringId.Android -> {
                context.getString(stringId.resId, *args)
            }
            is StringId.Named -> {
                val resId = context.resources.getIdentifier(stringId.name, "string", context.packageName)
                if (resId != 0) {
                    context.getString(resId, *args)
                } else {
                    "[$resId]"
                }
            }
        }
    }
}

class CoreStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
): CoreStringProvider {
    override val connectionErrorMessage: String = context.getString(R.string.connection_error)
    override val unknownErrorMessage: String = context.getString(R.string.unknown_error_message)
    override fun backendErrorMessage(code: Int, message: String): String = context.getString(R.string.backend_error_message, code, message)
}