package com.iota.core.dto.room

import com.iota.core.dto.device.DeviceGet
import com.iota.core.model.Device
import com.iota.core.model.Room


open class RoomGetSimple(room: Room) {
    var id: Long = 0
    var name: String = ""

    init {
        this.id = room.id!!
        this.name = room.name
    }
}

class RoomGet(room: Room) : RoomGetSimple(room) {
    var devices: Set<DeviceGet> = setOf()

    init {
        this.devices = room.devices.map { DeviceGet(it) }.toSet()
    }
}