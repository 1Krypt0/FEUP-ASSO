package com.iota.core.repository

import com.iota.core.model.Device
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : CrudRepository<Device, Long> {}
