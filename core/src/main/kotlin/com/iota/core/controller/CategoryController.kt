package com.iota.core.controller

import com.iota.core.dto.category.CategoryDeleted
import com.iota.core.dto.category.CategoryGet
import com.iota.core.dto.category.CategoryGetSimple
import com.iota.core.dto.category.CategoryUpdate
import com.iota.core.dto.model.CategoryDto
import com.iota.core.service.CategoryService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val service: CategoryService,
) {
    @GetMapping("/{id}")
    fun category(@PathVariable id: Long): CategoryGet {
        return CategoryGet(service.category(id))
    }

    @GetMapping("/")
    fun categories(): List<CategoryGetSimple> {
        return service.categories().map { CategoryGetSimple(it) }
    }

    @PostMapping("/new")
    fun newCategory(@Valid @RequestBody dto: CategoryDto): CategoryGetSimple {
        return CategoryGetSimple(service.new(dto))
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @Valid @RequestBody dto: CategoryUpdate): CategoryGetSimple {
        return CategoryGetSimple(service.update(id, dto))
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): CategoryDeleted {
        service.delete(id)

        return CategoryDeleted(id)
    }
}