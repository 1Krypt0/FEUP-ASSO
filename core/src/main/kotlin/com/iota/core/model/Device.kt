package com.iota.core.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

enum class NetworkStatus {
    CONNECTED,
    DISCONNECTED
}

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "UC_MAC", columnNames = ["macAddress"]),
    ], name = "device"
)
class Device {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull
    var dataTopic: String = ""

    @NotNull
    var actionTopic: String = ""

    @NotEmpty
    var name: String = ""

    var displayName: String? = ""

    @NotEmpty
    var macAddress: String = ""

    @ManyToOne
    var room: Room? = null

    var added: Boolean = false

    @ManyToOne
    var category: Category? = null

    @NotNull
    @Enumerated
    var status: NetworkStatus? = null

    @OneToMany(mappedBy = "device", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var deviceActions: Set<DeviceAction> = setOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Device) return false

        if (id != other.id) return false
        if (dataTopic != other.dataTopic) return false
        if (actionTopic != other.actionTopic) return false
        if (name != other.name) return false
        if (room != other.room) return false
        if (added != other.added) return false
        if (macAddress != other.macAddress) return false
        if (category != other.category) return false
        return status == other.status
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + dataTopic.hashCode()
        result = 31 * result + actionTopic.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + room.hashCode()
        result = 31 * result + added.hashCode()
        result = 31 * result + macAddress.hashCode()
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        return result
    }
}
