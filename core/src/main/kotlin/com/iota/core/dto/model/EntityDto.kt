package com.iota.core.dto.model

import jakarta.persistence.Entity

interface EntityDto<T> {
    fun create(): T
}
