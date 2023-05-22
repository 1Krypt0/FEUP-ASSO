package com.iota.core.model

import com.iota.core.dto.device.RequiredProperties
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import org.hibernate.annotations.Type

@Entity
@Table(name = "action", uniqueConstraints = [
    UniqueConstraint(name = "UC_ACTION_NAME", columnNames = ["name"]),
],)
class Action {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotEmpty
    var name: String = ""

    @NotEmpty
    var type: String = ""

    @Type(JsonType::class)
    @Lob
    @Column(columnDefinition = "json")
    var required: RequiredProperties = mutableListOf()

    var updatable: Boolean = true

    @OneToMany(mappedBy = "action", fetch = FetchType.LAZY)
    var deviceActions: Set<DeviceAction> = setOf()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Action) return false

        if (id != other.id) return false
        if (name != other.name) return false
        return required == other.required
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + required.hashCode()
        return result
    }
}