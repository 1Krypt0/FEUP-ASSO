package com.iota.core.dto.action

import com.iota.core.dto.device.Properties
import com.iota.core.model.DeviceAction

open class DeviceActionGetSimple(deviceAction: DeviceAction) {
    var id: Long = 0
    var name: String = ""
    private var displayName: String = ""
    var properties: Properties = mutableMapOf()
    var status: String = ""
    var actionName: String? = null

    init {
        this.id = deviceAction.id
        this.displayName = deviceAction.displayName
        this.properties = deviceAction.properties
        this.status = deviceAction.status
        this.name = deviceAction.name
        this.actionName = deviceAction.action?.name
    }
}

class DeviceActionGet(deviceAction: DeviceAction) : DeviceActionGetSimple(deviceAction) {
    var action: ActionGet = ActionGet()

    init {
        this.action = ActionGet(deviceAction.action!!)
        this.actionName = null
    }
}