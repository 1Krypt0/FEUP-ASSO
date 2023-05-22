package com.iota.core.model.workflows

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotEmpty

@Entity
class Workflow {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotEmpty
    var name: String = ""

    var active: Boolean = true
}
