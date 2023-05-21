package com.iota.core.model.discoverability

import kotlinx.serialization.Serializable

@Serializable
data class DiscoverableDevice(
    val mac: String,
    val name: String,
    val actions: List<DiscoverableDeviceAction>
)
