package com.iota.core.dto.model

import com.iota.core.dto.device.Properties
import com.iota.core.model.DeviceAction
import com.iota.core.validator.ValidActionId
import com.iota.core.validator.ValidDeviceActionProperties
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

@ValidDeviceActionProperties
class DeviceActionDto : EntityDto<DeviceAction> {
    @NotNull
    @ValidActionId
    var id: Long? = null;

    @NotEmpty
    var name: String = "";

    var properties: Properties = mutableMapOf();

    @NotNull
    var status: String? = "";

    override fun create(): DeviceAction {
        val entity = DeviceAction()
        entity.id = id
        entity.displayName = name
        entity.properties = properties
        entity.status = status

        return entity
    }
}
