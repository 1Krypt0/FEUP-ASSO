package com.iota.core.dto.category

import jakarta.validation.constraints.Size

class CategoryUpdate {
    @Size(min = 1, max = 255)
    var name: String? = null

    @Size(min = 1)
    var icon: String? = null
}