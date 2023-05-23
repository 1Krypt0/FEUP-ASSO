package com.iota.core.model.discoverability

import kotlinx.serialization.Serializable

@Serializable
class StatusUpdate(
    val id: String,
    val status: String
)

typealias DeviceStatusUpdate = List<StatusUpdate>
