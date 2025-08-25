package com.uandcode.messenger.core.essentials.exceptions

abstract class AppException(
    message: String,
    cause: Throwable? = null
): Exception(message, cause)

class UnknownException(
    message: String = "Unknown error"
): AppException(message)