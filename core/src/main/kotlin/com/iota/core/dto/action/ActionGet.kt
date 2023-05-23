package com.iota.core.dto.action

import com.fasterxml.jackson.annotation.JsonProperty
import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action

class ActionGet(action: Action) {
    var id: Long = 0
    var type: String = ""
    var name: String = ""

    @JsonProperty("updatable")
    private var updatable: Boolean = true

    @JsonProperty("required")
    private var required: RequiredProperties = mutableListOf()

    init {
        this.id = action.id
        this.name = action.name
        this.type = action.type
        this.updatable = action.updatable
        this.required = action.required
    }

    constructor() : this(Action())
}