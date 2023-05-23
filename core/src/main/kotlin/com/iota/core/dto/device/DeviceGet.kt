package com.iota.core.dto.device

import com.fasterxml.jackson.annotation.JsonProperty
import com.iota.core.dto.action.DeviceActionGetSimple
import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import com.iota.core.model.NetworkStatus

class DeviceGet(device: Device) {
    var id: Long = 0
    var name: String = ""
    @JsonProperty("macAddress")
    private var macAddress: String = ""
    var room: Long? = null
    @JsonProperty("added")
    private var added: Boolean = false
    var type: DeviceType? = DeviceType.LIGHT
    var status: NetworkStatus = NetworkStatus.DISCONNECTED
    @JsonProperty("actions")
    private var deviceActions: Set<DeviceActionGetSimple> = setOf()

    init {
        this.id = device.id
        this.name = device.name
        this.macAddress = device.macAddress
        this.type = device.type
        this.status = device.status!!
        this.room = device.room?.id
        this.added = device.added
        this.deviceActions = device.deviceActions.map { DeviceActionGetSimple(it) }.toSet()
    }
}