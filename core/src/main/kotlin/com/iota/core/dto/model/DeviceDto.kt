package com.iota.core.dto.model

import com.iota.core.model.Device
import com.iota.core.model.DeviceType

class DeviceDto(
    val name: String,
    val macAddress: String,
    var type: DeviceType,
) : EntityDto<Device> {

    override fun create(): Device {
        val entity = Device()
        entity.name = name
        entity.macAddress = macAddress
        entity.type = type

        return entity
    }
}