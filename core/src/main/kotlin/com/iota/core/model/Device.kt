package com.iota.core.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull

enum class NetworkStatus {
    CONNECTED,
    DISCONNECTED
}

@Entity
class Device {
    @Id
    @GeneratedValue
    private val id: Long? = null

    @NotNull
    private val topic: String = ""

    @NotNull
    private val name: String = ""

    @NotNull
    private val macAddress: String = ""
}


