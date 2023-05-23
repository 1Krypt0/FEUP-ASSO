package com.iota.core.dto.model

import com.iota.core.model.Category
import jakarta.validation.constraints.NotEmpty

class CategoryDto : EntityDto<Category> {
    @NotEmpty
    var name: String = ""

    @NotEmpty
    var icon: String = ""

    override fun create(): Category {
        val category = Category()
        category.name = name
        category.icon = icon

        return category
    }
}
