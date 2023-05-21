package com.iota.core.dto.model

import com.iota.core.dto.device.Properties
import com.iota.core.model.DeviceAction
import com.iota.core.model.discoverability.DiscoverableDeviceAction
import com.iota.core.validator.ValidActionName
import com.iota.core.validator.ValidDeviceActionProperties
import jakarta.validation.constraints.NotEmpty

@ValidDeviceActionProperties
class DeviceActionDto : EntityDto<DeviceAction> {
    constructor(deviceAction: DiscoverableDeviceAction) {
        deviceId = deviceAction.id
        name = deviceAction.name
        actionName = deviceAction.deviceAction
        displayName = deviceAction.name
        properties = deviceAction.properties?.toMutableMap() ?: mutableMapOf()
        status = deviceAction.status
    }

    @NotEmpty
    var name: String = ""

    @NotEmpty
    var deviceId: String = ""

    @NotEmpty
    @ValidActionName
    var actionName: String = ""

    @NotEmpty
    var displayName: String = ""

    var properties: Properties = mutableMapOf()

    @NotEmpty
    var status: String = ""

    override fun create(): DeviceAction {
        val entity = DeviceAction()
        entity.displayName = displayName
        entity.properties = properties
        entity.status = status
        entity.name = name
        entity.idDevice = deviceId

        return entity
    }
}
