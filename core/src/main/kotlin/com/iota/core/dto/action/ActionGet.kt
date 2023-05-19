package com.iota.core.dto.action

import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class ActionGet (action: Action) {
    @NotNull
    var id: Long? = null
    var type: String = ""
    @NotEmpty
    var name: String = ""
    var required: RequiredProperties = mutableListOf()

    init {
        this.id = action.id
        this.name = action.name
        this.type = action.type
        this.required = action.required
    }

    constructor() : this(Action())
}