package com.iota.core.dto.category

import com.iota.core.model.Category

open class CategoryGetSimple(category: Category) {
    var id: Long = 0
    var name: String = ""
    var icon: String = ""

    init {
        this.id = category.id
        this.name = category.name
        this.icon = category.icon
    }
}

class CategoryGet(category: Category) : CategoryGetSimple(category){

}
