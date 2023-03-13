package com.iota.core.dto.model

import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import jakarta.persistence.Column
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class DeviceDto : EntityDto<Device> {
    @NotEmpty
    var name: String = "";

    @NotEmpty
    var macAddress: String = "";

    @NotNull
    var type: DeviceType? = null;

    override fun create(): Device {
        val entity = Device()
        entity.name = name
        entity.macAddress = macAddress
        entity.type = type

        return entity
    }
}