package com.iota.core.dto.device

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
class DeviceUpdate {
    @Size(min = 1, max = 255)
    val name: String? = null

    @Min(1)
    val room: Long? = null
}