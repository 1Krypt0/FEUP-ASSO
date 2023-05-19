package com.iota.core.dto.device

import com.iota.core.dto.action.DeviceActionGet
import com.iota.core.dto.action.DeviceActionGetSimple
import com.iota.core.model.Device
import com.iota.core.model.DeviceAction
import com.iota.core.model.DeviceType
import com.iota.core.model.NetworkStatus
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

class DeviceGet (device : Device) {
    var id: Long = 0
    var dataTopic: String = ""
    var actionTopic: String = ""
    var name: String = ""
    var macAddress: String = ""
    var type: DeviceType = DeviceType.LIGHT
    var status: NetworkStatus = NetworkStatus.DISCONNECTED
    var deviceActions: Set<DeviceActionGetSimple> = setOf()

    init {
        this.id = device.id!!
        this.dataTopic = device.dataTopic
        this.actionTopic = device.actionTopic
        this.name = device.name
        this.macAddress = device.macAddress
        this.type = device.type!!
        this.status = device.status!!
        this.deviceActions = device.deviceActions.map { DeviceActionGetSimple(it) }.toSet()
    }
}