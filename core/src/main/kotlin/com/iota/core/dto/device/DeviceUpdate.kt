package com.iota.core.dto.device

import com.fasterxml.jackson.annotation.JsonInclude
import com.iota.core.model.DeviceType
import com.iota.core.validator.ValueOfEnum
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
class DeviceUpdate {
    @Size(min = 1, max = 255)
    val name: String? = null

    @Min(1)
    val room: Long? = null

    @ValueOfEnum(enumClass = DeviceType::class)
    var type: String? = null
}