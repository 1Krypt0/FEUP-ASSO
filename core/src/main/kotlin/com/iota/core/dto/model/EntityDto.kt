package com.iota.core.dto.model

interface EntityDto<T> {
    fun create(): T
}
