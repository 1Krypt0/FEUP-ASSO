package com.iota.core.dto.model

import com.iota.core.dto.device.Properties
import com.iota.core.model.DeviceAction
import com.iota.core.validator.ValidActionId
import com.iota.core.validator.ValidDeviceActionProperties
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@ValidDeviceActionProperties
class DeviceActionDto : EntityDto<DeviceAction> {
    @NotNull
    @Min(1)
    @ValidActionId
    var id: Long = 0

    @NotEmpty
    var name: String = ""

    var properties: Properties = mutableMapOf()

    @NotEmpty
    var status: String = ""

    override fun create(): DeviceAction {
        val entity = DeviceAction()
        entity.displayName = name
        entity.properties = properties
        entity.status = status

        return entity
    }
}
