package com.iota.core.model

import com.iota.core.dto.device.Properties
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity
@Table(name="device_action")
class DeviceAction {
    @Id
    @GeneratedValue
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    var device: Device? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_id")
    var action: Action? = null

    @NotNull
    var displayName: String? = null
    var description: String? = null
    @Type(JsonType::class)
    @Lob
    @Column(columnDefinition = "json")
    var properties: Properties? = null
    @NotNull
    var status: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DeviceAction) return false

        if (id != other.id) return false
        if (displayName != other.displayName) return false
        if (description != other.description) return false
        if (properties != other.properties) return false
        return status == other.status
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (displayName?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (properties?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        return result
    }
}