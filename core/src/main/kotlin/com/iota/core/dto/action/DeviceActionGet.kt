package com.iota.core.dto.action

import com.iota.core.dto.device.Properties
import com.iota.core.model.DeviceAction

open class DeviceActionGetSimple(deviceAction: DeviceAction) {
    var id: Long = 0
    var displayName: String = ""
    var description: String = ""
    var properties: Properties = mutableMapOf()
    var status: String = ""

    init {
        this.id = deviceAction.id
        this.displayName = deviceAction.displayName
        this.description = deviceAction.description
        this.properties = deviceAction.properties
        this.status = deviceAction.status
    }
}

class DeviceActionGet(deviceAction: DeviceAction) : DeviceActionGetSimple(deviceAction) {
    var action: ActionGet = ActionGet()

    init {
        this.action = ActionGet(deviceAction.action!!)
    }
}