package com.uandcode.messenger.core.essentials.resources

interface StringResources {
    fun getString(resId: Int, vararg args: Any): String
}

interface StringResourcesV2 {
    fun getString(stringId: StringId, vararg args: Any): String

    fun getString(resId: Int, vararg args: Any): String {
        return getString(StringId.Android(resId), *args)
    }

    fun getString(name: String, vararg args: Any): String {
        return getString(StringId.Named(name), *args)
    }
}

interface StringProvider

interface CoreStringProvider: StringProvider {
    val connectionErrorMessage: String
    val unknownErrorMessage: String
    fun backendErrorMessage(code: Int, message: String): String
}