package com.uandcode.messenger.core.essentials.exceptions.mapper

interface ExceptionToMessageMapper {
    fun getLocalizedMessage(exception: Exception): String

    companion object : ExceptionToMessageMapper {
        private var mapper: ExceptionToMessageMapper = EmptyExceptionToMessageMapper()

        fun init(mapper: ExceptionToMessageMapper) {
            this.mapper = mapper
        }

        fun reset() {
            this.mapper = EmptyExceptionToMessageMapper()
        }

        override fun getLocalizedMessage(exception: Exception): String =
            mapper.getLocalizedMessage(exception)
    }
}