package com.iota.core.dto.device

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class DeviceUpdate {
    @NotEmpty
    val name: String? = null
    @Min(1)
    val room: Long? = null
}