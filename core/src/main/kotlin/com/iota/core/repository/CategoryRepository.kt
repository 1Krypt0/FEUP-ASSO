package com.iota.core.repository

import com.iota.core.model.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CategoryRepository : CrudRepository<Category, Long> {
    fun findByName(name: String): Optional<Category>
}
