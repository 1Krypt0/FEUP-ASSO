package com.iota.core.model.discoverability

import kotlinx.serialization.Serializable

@Serializable
data class DiscoverableDeviceAction(
    val id: String,
    val deviceAction: String,
    val name: String,
    val displayName: String,
    val properties: Map<String, String>? = null,
    val status: String,
)
