package com.iota.core.exception.category

class CategoryNotFoundException(
    override val message: String?,
    override val cause: Throwable?,
    val id: Long,
) : Exception(message, cause)

