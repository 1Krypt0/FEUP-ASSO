package com.iota.core.dto.model

import com.iota.core.dto.device.RequiredProperties
import jakarta.validation.constraints.NotEmpty

class ActionDto {
    @NotEmpty
    var name: String = ""

    var required: RequiredProperties? = null
}