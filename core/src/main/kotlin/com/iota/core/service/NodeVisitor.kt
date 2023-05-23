package com.iota.core.service;

import com.iota.core.model.discoverability.StatusUpdate
import com.iota.core.model.workflows.ActionNode
import com.iota.core.model.workflows.ConditionNode
import com.iota.core.model.workflows.ConditionType
import com.iota.core.model.workflows.ConditionType.*
import com.iota.core.model.workflows.EventNode
import com.iota.core.model.workflows.Node
import com.iota.core.model.workflows.OperatorNode
import com.iota.core.model.workflows.OperatorType.*
import com.iota.core.queue.Broker
import com.iota.core.repository.ConditionNodeRepository
import com.iota.core.repository.EventNodeRepository
import com.iota.core.repository.OperatorNodeRepository
import kotlinx.serialization.json.Json

class NodeVisitor (
    private val conditionNodeRepository: ConditionNodeRepository,
    private val operatorNodeRepository: OperatorNodeRepository,
    private val eventNodeRepository: EventNodeRepository,
    private val broker: Broker
) {
    private fun propagate(node: Node, value: String) {
        node.successors.forEach {this.update(it, value) }
    }

    fun update(node: Node, value: String) {
        updateEvent(node, value)
        updateCondition(node, value)
        updateOperator(node, value)
        updateAction(node, value)
    }

    private fun updateEvent(node: Node, incoming: String) {
        if (node !is EventNode) return
        propagate(node, incoming)
    }
    private fun updateCondition(node: Node, incoming: String) {
        if (node !is ConditionNode) return

        val conditionMet = when(node.conditionType) {
            EQUAL -> incoming == node.value
            MORE_THAN -> incoming.toFloat() > node.value.toFloat()
            LESS_THAN -> incoming.toFloat() < node.value.toFloat()
            else -> false
        }

        if (conditionMet) {
            node.conditionMet = true
            conditionNodeRepository.save(node)
        }

        propagate(
            node,
            if (conditionMet) "1" else "0",
        )
    }
    private fun updateOperator(node: Node, incoming: String) {
        if (node !is OperatorNode) return

        val predecessors = conditionNodeRepository.findPredecessorsByNode(node)

        val validConditions = predecessors.count { it.conditionMet }
        val result = when(node.operatorType) {
            AND -> {
                if (validConditions == predecessors.size) {
                    node.conditionMet = true
                    operatorNodeRepository.save(node)
                    "1"
                } else {
                    "0"
                }
            }

            OR -> {
                if (validConditions > 0) {
                    node.conditionMet = true
                    operatorNodeRepository.save(node)
                    "1"
                } else {
                    "0"
                }
            }

            else -> "0"
        }

        propagate(node, result)
    }
    private fun updateAction(node: Node, incoming: String) {
        if (node !is ActionNode) return

        val predecessors = operatorNodeRepository.findPredecessorsByNode(node)
        val validConditions = predecessors.count { it.conditionMet }
        if (validConditions < predecessors.size) {
            return
        }

        node.deviceAction?.let {
            val statusUpdate = StatusUpdate(node.deviceAction.idDevice, node.value)
            val json = Json.encodeToString(StatusUpdate.serializer(), statusUpdate)
            node.deviceAction.device?.actionTopic?.let { topic -> broker.addToTopic(topic, json) }
        }
    }
}
