package com.iota.core.dto.model

import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import com.iota.core.model.discoverability.DiscoverableDevice
import com.iota.core.validator.UniqueMAC
import com.iota.core.validator.ValueOfEnum
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class DeviceDto : EntityDto<Device> {
    constructor(value: DiscoverableDevice) {
        name = value.name
        macAddress = value.mac
        actions = value.actions.map {
            DeviceActionDto(it)
        }.toSet()
    }
    @NotEmpty
    var name: String = ""

    @NotEmpty
    @UniqueMAC
    var macAddress: String = ""

    @NotEmpty
    @ValueOfEnum(enumClass = DeviceType::class)
    var type: String = ""

    @Valid
    var actions: Set<DeviceActionDto> = setOf()

    override fun create(): Device {
        val entity = Device()
        entity.name = name
        entity.macAddress = macAddress

        return entity
    }
}