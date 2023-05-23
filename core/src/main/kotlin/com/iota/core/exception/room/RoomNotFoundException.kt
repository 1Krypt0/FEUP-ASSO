package com.iota.core.exception.room

class RoomNotFoundException(
    override val message: String?,
    override val cause: Throwable?,
    val id: Long,
) : Exception(message, cause)

