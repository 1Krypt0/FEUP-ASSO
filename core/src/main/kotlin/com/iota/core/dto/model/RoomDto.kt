package com.iota.core.dto.model

import com.iota.core.model.Room
import jakarta.validation.constraints.NotEmpty

class RoomDto : EntityDto<Room> {
    @NotEmpty
    var name: String = ""

    override fun create(): Room {
        val room = Room()
        room.name = name

        return room
    }
}