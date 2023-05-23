package com.iota.core.startup

import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action
import com.iota.core.model.Category
import com.iota.core.model.workflows.ActionNode
import com.iota.core.model.workflows.ConditionNode
import com.iota.core.model.workflows.ConditionType
import com.iota.core.model.workflows.EventNode
import com.iota.core.model.workflows.OperatorNode
import com.iota.core.model.workflows.Workflow
import com.iota.core.repository.ActionNodeRepository
import com.iota.core.repository.ActionRepository
import com.iota.core.repository.CategoryRepository
import com.iota.core.repository.ConditionNodeRepository
import com.iota.core.repository.DeviceRepository
import com.iota.core.repository.EventNodeRepository
import com.iota.core.repository.OperatorNodeRepository
import com.iota.core.repository.WorkflowsRepository
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DatabaseSeeder(
    val actionRepository: ActionRepository,
    val categoryRepository: CategoryRepository,
    val deviceRepository: DeviceRepository,
    private val conditionNodeRepository: ConditionNodeRepository,
    private val operatorNodeRepository: OperatorNodeRepository,
    private val workflowsRepository: WorkflowsRepository,
    private val actionNodeRepository: ActionNodeRepository,
    private val eventNodeRepository: EventNodeRepository,
) {

    private fun seedActions() {
        addAction("toggle", "bool")
        addAction("boolean", "bool")
        addAction("range", "number", true, mutableListOf("min", "max", "step"))
        addAction("rgb", "number")
        addAction("readonly-number", "number", false)
    }

    private fun seedCategories() {
        addCategory("Light", "")
        addCategory("Media", "")
        addCategory("Climate", "")
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

    private fun addCategory(name: String, icon: String) {
        if (categoryRepository.findByName(name).isPresent) {
            println("Already stored $name")
            return
        }
        val category = Category()
        category.name = name;
        // TODO: Change to svg
        category.icon = "";
        categoryRepository.save(category)
        println("Stored ${category.name}")
    }

    fun seedWorkflowOn() {
        var targetLight = deviceRepository.findById(3).get()
        var sensor = deviceRepository.findById(1).get()

        var lightNode = ActionNode()
        lightNode.value = "1"
        lightNode.deviceAction = targetLight.deviceActions.toList()[0]
//        actionNodeRepository.save(lightNode)

        var conditionNode = ConditionNode()
        conditionNode.value = "60"
        conditionNode.conditionType = ConditionType.MORE_THAN
        conditionNode.successors = mutableSetOf(lightNode)
//        conditionNodeRepository.save(conditionNode)

        var eventNode = EventNode()
        eventNode.deviceAction = sensor.deviceActions.toList()[0]
        eventNode.successors = mutableSetOf(conditionNode)
        eventNodeRepository.save(eventNode)

        var workflow = Workflow()
        workflow.entryNodes = listOf(eventNode)
        workflow.active = true
        workflow.name = "Teste"

        workflowsRepository.save(workflow)
    }

    fun seedWorkflowsOff() {
        var targetLight = deviceRepository.findById(3).get()
        var sensor = deviceRepository.findById(1).get()

        var lightNode = ActionNode()
        lightNode.value = "0"
        lightNode.deviceAction = targetLight.deviceActions.toList()[0]
//        actionNodeRepository.save(lightNode)

        var conditionNode = ConditionNode()
        conditionNode.value = "60"
        conditionNode.conditionType = ConditionType.LESS_THAN
        conditionNode.successors = mutableSetOf(lightNode)
//        conditionNodeRepository.save(conditionNode)

        var eventNode = EventNode()
        eventNode.deviceAction = sensor.deviceActions.toList()[0]
        eventNode.successors = mutableSetOf(conditionNode)
        eventNodeRepository.save(eventNode)

        var workflow = Workflow()
        workflow.entryNodes = listOf(eventNode)
        workflow.active = true
        workflow.name = "Teste"

        workflowsRepository.save(workflow)
    }

    @EventListener
    fun seed(event: ContextRefreshedEvent) {
        seedActions()
        seedCategories()
        workflowsRepository.deleteAll()
        seedWorkflowsOff()
        seedWorkflowOn()
    }
}
