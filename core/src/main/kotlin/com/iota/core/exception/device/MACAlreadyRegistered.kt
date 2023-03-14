package com.iota.core.exception.device

class MACAlreadyRegistered(
    override val message: String?,
    override val cause: Throwable?,
    val field: String
) : Exception(message, cause)