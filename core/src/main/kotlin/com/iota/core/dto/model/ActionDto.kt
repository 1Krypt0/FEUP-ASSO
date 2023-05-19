package com.iota.core.dto.model

import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action
import jakarta.validation.constraints.NotEmpty

class ActionDto : EntityDto<Action> {
    @NotEmpty
    var name: String = ""

    @NotEmpty
    var type: String = ""

    var required: RequiredProperties = mutableListOf()

    override fun create(): Action {
        val entity = Action()
        entity.name = name
        entity.type = type
        entity.required = required

        return entity
    }
}