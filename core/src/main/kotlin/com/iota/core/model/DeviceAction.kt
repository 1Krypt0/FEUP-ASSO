package com.iota.core.model

import com.iota.core.dto.device.Properties
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import org.hibernate.annotations.Type

@Entity
@Table(name = "device_action")
class DeviceAction {
    @Id
    @GeneratedValue
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    var device: Device? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_id")
    var action: Action? = null

    @NotEmpty
    var displayName: String = ""

    var description: String = ""

    @Type(JsonType::class)
    @Lob
    @Column(columnDefinition = "json")
    var properties: Properties = mutableMapOf()

    @NotEmpty
    var status: String = ""

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
        var result = id.hashCode()
        result = 31 * result + displayName.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + properties.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }
}