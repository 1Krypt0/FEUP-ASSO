package com.iota.core.dto.device

import com.iota.core.dto.action.DeviceActionGet
import com.iota.core.model.Device
import com.fasterxml.jackson.annotation.JsonProperty
import com.iota.core.model.NetworkStatus

class DeviceGet(device: Device) {
    var id: Long = 0
    var name: String = ""
    var displayName: String = ""

    @JsonProperty("macAddress")
    private var macAddress: String = ""
    var room: Long? = null

    @JsonProperty("added")
    private var added: Boolean = false
    var category: Long? = null
    var status: NetworkStatus = NetworkStatus.DISCONNECTED

    @JsonProperty("actions")
    private var deviceActions: Set<DeviceActionGet> = setOf()

    init {
        this.id = device.id
        this.name = device.name
        this.displayName = device.displayName ?: device.name
        this.macAddress = device.macAddress
        this.category = device.category?.id
        this.status = device.status!!
        this.room = device.room?.id
        this.added = device.added
        this.deviceActions = device.deviceActions.map { DeviceActionGet(it) }.toSet()
    }
}
