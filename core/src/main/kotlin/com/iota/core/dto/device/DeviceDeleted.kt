package com.iota.core.dto.device

class DeviceDeleted(id: Long) {
    var message: String = ""

    init {
        this.message = "Device $id deleted successfully"
    }
}