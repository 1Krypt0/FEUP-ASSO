package com.iota.core.startup

import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action
import com.iota.core.repository.ActionRepository
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DatabaseSeeder(
    val actionRepository: ActionRepository
) {

    private fun seedActions() {
        addAction("toggle", "bool")
        addAction("boolean", "bool")
        addAction("range", "number", true, mutableListOf("min", "max", "step"))
        addAction("rgb", "number")
        addAction("readonly-number", "number", false)
    }

    private fun addAction(name: String, type: String, updatable: Boolean = true, required: RequiredProperties? = null) {
        if (actionRepository.findByName(name).isPresent) {
            println("Already stored $name")
            return
        }
        val action = Action()
        action.name = name
        action.type = type
        action.updatable = updatable
        required?.let { action.required = it }
        actionRepository.save(action)
        println("Stored ${action.name}")
    }

    @EventListener
    public fun seed(event: ContextRefreshedEvent) {
        seedActions()
    }
}
