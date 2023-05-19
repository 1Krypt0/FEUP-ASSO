package com.iota.core.model

import com.iota.core.dto.device.RequiredProperties
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import org.jetbrains.annotations.NotNull

@Entity
@Table(name="action")
class Action {
    @Id
    @GeneratedValue
    var id: Long? = null

    @NotNull
    var name: String? = null

    @Type(JsonType::class)
    @Lob
    @Column(columnDefinition = "json")
    var required: RequiredProperties? = null

    @OneToMany(mappedBy = "action")
    var deviceActions: Set<DeviceAction> = setOf()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Action) return false

        if (id != other.id) return false
        if (name != other.name) return false
        return required == other.required
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (required?.hashCode() ?: 0)
        return result
    }
}