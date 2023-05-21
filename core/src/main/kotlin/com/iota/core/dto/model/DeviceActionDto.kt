package com.iota.core.dto.model

import com.iota.core.dto.device.Properties
import com.iota.core.model.DeviceAction
import com.iota.core.validator.ValidActionName
import com.iota.core.validator.ValidDeviceActionProperties
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@ValidDeviceActionProperties
class DeviceActionDto : EntityDto<DeviceAction> {
    @NotEmpty
    var name: String = ""

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

        return entity
    }
}
