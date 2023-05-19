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
}