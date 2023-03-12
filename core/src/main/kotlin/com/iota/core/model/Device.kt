package com.iota.core.model

import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull

enum class NetworkStatus {
    CONNECTED,
    DISCONNECTED
}

enum class DeviceType {
    LIGHT,
    MEDIA,
    CLIMATE
}

@Entity
class Device {
    @Id
    @GeneratedValue
    var id: Long? = null

    @NotNull
    var topic: String = ""

    @NotNull
    var name: String = ""

    @NotNull
    var macAddress: String = ""

    @NotNull
    @Enumerated
    var type: DeviceType? = null

    @NotNull
    @Enumerated
    var status: NetworkStatus? = null
}


