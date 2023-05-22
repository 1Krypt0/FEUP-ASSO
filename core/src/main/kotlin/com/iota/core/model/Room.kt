package com.iota.core.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotEmpty

@Entity
class Room {
    @Id
    @GeneratedValue
    var id: Long? = null

    @NotEmpty
    var name: String = ""
}