package com.iota.core.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
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
@Table(uniqueConstraints = [
    UniqueConstraint(name = "UC_MAC", columnNames = ["macAddress"]),
], name = "device")
class Device {
    @Id
    @GeneratedValue
    var id: Long? = null

    @NotNull
    var dataTopic: String = ""

    @NotNull
    var actionTopic: String = ""

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

    @OneToMany(mappedBy = "device")
    var actions: List<Action> = listOf()
}
