package com.iota.core.repository

import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DeviceRepository : CrudRepository<Device, Long> {
    fun findAllByType(type: DeviceType): Iterable<Device>

    fun findDeviceByMacAddress(macAddress: String): Optional<Device>
}
