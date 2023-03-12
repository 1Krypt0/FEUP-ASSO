package com.iota.core.repository

import com.iota.core.model.Device
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : CrudRepository<Device, Long> {}
