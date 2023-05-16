package com.iota.core.model

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name="action")
class Action {
    @Id
    @GeneratedValue
    var id: Long? = null

    @NotNull
    var name: String? = null

    @Lob
    var actionValue: String? = null

    fun setValue(data: Any) {
        actionValue = ObjectMapper().writeValueAsString(data)
    }

    fun getValue(): Any {
        return ObjectMapper().readValue(actionValue, Any::class.java)
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "device_id")
    var device: Device? = null
}