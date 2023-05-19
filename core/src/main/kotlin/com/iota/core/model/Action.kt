package com.iota.core.model

import com.iota.core.dto.action.ActionGet
import com.iota.core.dto.device.DeviceGet
import com.iota.core.dto.device.RequiredProperties
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import org.hibernate.annotations.Type
import org.jetbrains.annotations.NotNull
import org.springframework.validation.annotation.Validated

@Entity
@Table(name="action")
class Action {
    @Id
    @GeneratedValue
    var id: Long? = null

    @NotEmpty
    var name: String = ""

    @NotEmpty
    var type: String = ""

    @Type(JsonType::class)
    @Lob
    @Column(columnDefinition = "json")
    var required: RequiredProperties = mutableListOf()

    @OneToMany(mappedBy = "action", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
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
        result = 31 * result + name.hashCode()
        result = 31 * result + required.hashCode()
        return result
    }
}