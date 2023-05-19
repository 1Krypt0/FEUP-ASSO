package com.iota.core.dto.action

import com.iota.core.dto.device.Properties
import com.iota.core.model.Action
import com.iota.core.model.Device
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import org.hibernate.annotations.Type

class DeviceActionGet {
    var id: Long = 0
    var action: ActionGet = ActionGet()
    var displayName: String = ""
    var description: String = ""
    var properties: Properties = mutableMapOf()
    var status: String = ""
}