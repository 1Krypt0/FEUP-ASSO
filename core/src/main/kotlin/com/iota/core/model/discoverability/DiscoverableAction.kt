package com.iota.core.model.discoverability

import kotlinx.serialization.Serializable

@Serializable
enum class ActionType {
    BOOL,
    INT,
    STRING,
    INT_ARRAY,
}

@Serializable
data class DiscoverableAction(
    val id: String,
    val name: String,
    val type: ActionType,
)
