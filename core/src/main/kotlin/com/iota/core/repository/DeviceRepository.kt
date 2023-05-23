package com.iota.core.repository

import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DeviceRepository : CrudRepository<Device, Long> {
    fun findAllByRoomId(roomId: Long): Iterable<Device>
    fun findAllByType(type: DeviceType): Iterable<Device>
    fun findAllByTypeAndRoomId(type: DeviceType, roomId: Long): MutableIterable<Device>

    fun findDeviceByMacAddress(macAddress: String): Optional<Device>
}
