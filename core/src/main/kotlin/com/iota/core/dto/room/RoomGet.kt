package com.iota.core.dto.room

import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action
import com.iota.core.model.Room

class RoomGet(room: Room) {
    var id: Long = 0
    var name: String = ""

    init {
        this.id = room.id!!
        this.name = room.name
    }
}