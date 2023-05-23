package com.iota.core.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "room")
class Room {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotEmpty
    var name: String = ""

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    var devices: Set<Device> = setOf()
}