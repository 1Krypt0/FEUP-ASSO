package com.iota.core.model

import com.iota.core.dto.device.DeviceGet
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
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

    @NotEmpty
    var name: String = ""

    @NotEmpty
    var macAddress: String = ""

    @NotNull
    @Enumerated
    var type: DeviceType? = null

    @NotNull
    @Enumerated
    var status: NetworkStatus? = null

    @OneToMany(mappedBy = "device", fetch = FetchType.EAGER)
    var deviceActions: Set<DeviceAction> = setOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Device) return false

        if (id != other.id) return false
        if (dataTopic != other.dataTopic) return false
        if (actionTopic != other.actionTopic) return false
        if (name != other.name) return false
        if (macAddress != other.macAddress) return false
        if (type != other.type) return false
        return status == other.status
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + dataTopic.hashCode()
        result = 31 * result + actionTopic.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + macAddress.hashCode()
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        return result
    }

    fun toDeviceGet() : DeviceGet {
        val deviceGet = DeviceGet()

        deviceGet.id = this.id!!
        deviceGet.name = this.name
        deviceGet.macAddress = this.macAddress
        deviceGet.type = this.type!!
        deviceGet.status = this.status!!
        deviceGet.deviceActions = this.deviceActions.map { it.toDeviceActionGet() }.toSet()
        deviceGet.actionTopic = this.actionTopic
        deviceGet.dataTopic = this.dataTopic

        return deviceGet
    }
}
