package com.iota.core.dto.category

class CategoryDeleted(id: Long) {
    var message: String = ""

    init {
        this.message = "Category $id deleted successfully"
    }
}