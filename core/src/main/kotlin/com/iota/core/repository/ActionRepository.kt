package com.iota.core.repository

import com.iota.core.model.Action
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ActionRepository : CrudRepository<Action, Long> {
    fun findByName(name: String): Optional<Action>
}