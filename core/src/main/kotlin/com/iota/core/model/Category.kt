package com.iota.core.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
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
}