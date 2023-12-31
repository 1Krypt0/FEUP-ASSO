package com.iota.core.repository

import com.iota.core.model.DeviceAction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceActionRepository : CrudRepository<DeviceAction, Long>