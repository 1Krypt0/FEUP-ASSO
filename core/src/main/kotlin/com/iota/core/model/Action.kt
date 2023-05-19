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
}