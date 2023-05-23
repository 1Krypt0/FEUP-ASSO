package com.iota.core.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "category")
class Category {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotEmpty
    var name: String = ""

    var icon: String = ""

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    var devices: Set<Device> = setOf()
}