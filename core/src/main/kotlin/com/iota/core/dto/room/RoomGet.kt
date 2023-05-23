package com.iota.core.dto.room

import com.iota.core.dto.device.DeviceGet
import com.iota.core.model.Device
import com.iota.core.model.Room

class RoomGet(room: Room) {
    var id: Long = 0
    var name: String = ""
    var devices: Set<DeviceGet> = setOf()

    init {
        this.id = room.id!!
        this.name = room.name
        this.devices = room.devices.map { DeviceGet(it) }.toSet()
    }
}