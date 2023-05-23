package com.iota.core.service

import com.iota.core.dto.category.CategoryUpdate
import com.iota.core.dto.model.CategoryDto
import com.iota.core.exception.category.CategoryNotFoundException
import com.iota.core.model.Category
import com.iota.core.repository.CategoryRepository
import com.iota.core.repository.DeviceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService {
    @Autowired
    private lateinit var categoryRepository: CategoryRepository
    @Autowired
    private lateinit var deviceRepository: DeviceRepository

    fun category(id: Long): Category {
        try {
            val category: Optional<Category> = categoryRepository.findById(id)

            return category.get()
        } catch (ex: NoSuchElementException) {
            throw CategoryNotFoundException(ex.message, ex.cause, id)
        }
    }

    fun new(dto: CategoryDto): Category {
        val category: Category = dto.create()

        try {
            categoryRepository.save(category)
        } catch (ex: DataIntegrityViolationException) {
            throw ex
        }

        return category
    }

    fun categories(): MutableIterable<Category> {
        return categoryRepository.findAll()
    }

    fun update(id: Long, dto: CategoryUpdate): Category {
        val category: Category = category(id)

        if (dto.name != null) {
            category.name = dto.name!!
        }

        if (dto.icon != null) {
            category.icon = dto.icon!!
        }

        try {
            categoryRepository.save(category)
        } catch (ex: DataIntegrityViolationException) {
            throw ex
        }

        return category
    }

    fun delete(id: Long) {
        val category: Category = category(id)

        categoryRepository.delete(category)
    }
}
