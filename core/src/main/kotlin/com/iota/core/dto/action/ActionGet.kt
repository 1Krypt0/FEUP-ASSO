package com.iota.core.dto.action

import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action

class ActionGet(action: Action) {
    var id: Long = 0
    var type: String = ""
    var name: String = ""
    var updatable: Boolean = true
    var required: RequiredProperties = mutableListOf()

    init {
        this.id = action.id
        this.name = action.name
        this.type = action.type
        this.updatable = action.updatable
        this.required = action.required
    }

    constructor() : this(Action())
}