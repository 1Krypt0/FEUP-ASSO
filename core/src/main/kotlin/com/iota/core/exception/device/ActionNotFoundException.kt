package com.iota.core.exception.device

class ActionNotFoundException(
    override val message: String?,
    override val cause: Throwable?,
    val id: Long,
) : Exception(message, cause)