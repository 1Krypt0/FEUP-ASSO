package com.iota.core.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "room")
class Room {
    @Id
    @GeneratedValue
    var id: Long? = null

    @NotEmpty
    var name: String = ""

    @OneToMany(mappedBy = "room")
    var devices: Set<Device> = setOf()
}