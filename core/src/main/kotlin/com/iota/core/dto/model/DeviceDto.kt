package com.iota.core.dto.model

import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import com.iota.core.validator.UniqueMAC
import com.iota.core.validator.ValueOfEnum
import jakarta.persistence.Column
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class DeviceDto : EntityDto<Device> {
    @NotEmpty
    var name: String = "";

    @NotEmpty
    @UniqueMAC
    var macAddress: String = "";

    @NotEmpty
    @ValueOfEnum(enumClass = DeviceType::class)
    var type: String = "";

    @NotNull
    // TODO: Add room validation
    var room: Int = 0;

    @Valid
    var actions: Set<DeviceActionDto> = setOf();

    override fun create(): Device {
        val entity = Device()
        entity.name = name
        entity.macAddress = macAddress
        entity.type = DeviceType.valueOf(type);

        return entity
    }
}