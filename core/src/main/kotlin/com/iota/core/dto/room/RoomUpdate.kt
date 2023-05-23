package com.iota.core.dto.room

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
class RoomUpdate {
    @Size(min = 1, max = 255)
    var name: String? = null
}