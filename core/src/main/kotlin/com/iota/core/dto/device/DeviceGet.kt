package com.iota.core.dto.device

import com.iota.core.dto.action.DeviceActionGetSimple
import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import com.iota.core.model.NetworkStatus

class DeviceGet(device: Device) {
    var id: Long = 0
    var dataTopic: String = ""
    var actionTopic: String = ""
    var name: String = ""
    var macAddress: String = ""
    var room: Long? = null
    var added: Boolean = false
    var type: DeviceType? = DeviceType.LIGHT
    var status: NetworkStatus = NetworkStatus.DISCONNECTED
    var deviceActions: Set<DeviceActionGetSimple> = setOf()

    init {
        this.id = device.id
        this.dataTopic = device.dataTopic
        this.actionTopic = device.actionTopic
        this.name = device.name
        this.macAddress = device.macAddress
        this.type = device.type
        this.status = device.status!!
        this.room = device.room?.id
        this.added = device.added
        this.deviceActions = device.deviceActions.map { DeviceActionGetSimple(it) }.toSet()
    }
}