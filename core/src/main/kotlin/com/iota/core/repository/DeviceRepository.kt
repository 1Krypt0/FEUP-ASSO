package com.iota.core.repository

import com.iota.core.model.Device
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DeviceRepository : CrudRepository<Device, Long> {
    fun findAllByRoomId(roomId: Long): Iterable<Device>
    fun findAllByCategoryId(category: Long): Iterable<Device>
    fun findAllByCategoryIdAndRoomId(type: Long, roomId: Long): MutableIterable<Device>

    fun findDeviceByMacAddress(macAddress: String): Optional<Device>
}
