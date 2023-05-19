package com.iota.core.dto.action

import com.iota.core.dto.device.RequiredProperties
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class ActionGet {
    @NotNull
    var id: Long? = null
    var type: String = ""
    @NotEmpty
    var name: String = ""

    var required: RequiredProperties = mutableListOf()
}