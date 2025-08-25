package com.uandcode.messenger.core.essentials.resources

sealed class StringId {
    data class Android(val resId: Int): StringId()

    data class Named(val name: String): StringId()
}