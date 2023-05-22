package com.iota.core.model.workflows

import com.iota.core.model.Device
import com.iota.core.model.DeviceAction
import com.iota.core.model.discoverability.StatusUpdate
import com.iota.core.repository.NodeRepositoriesHolder
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotEmpty
import kotlinx.serialization.json.Json
import org.jetbrains.annotations.NotNull


@Inheritance
@Entity
@Table(name = "node")
abstract class Node {
    @Id
    @GeneratedValue
    var id: Long = 0

    @NotNull
    var blockNumber: Int = 0

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var successors: MutableSet<Node> = mutableSetOf()

    var conditionMet: Boolean = false

    protected fun propagate(value: String, repositoriesHolder: NodeRepositoriesHolder) {
        successors.forEach { it.update(value, repositoriesHolder) }
    }

    abstract fun update(incoming: String, repositoriesHolder: NodeRepositoriesHolder)
}

enum class ConditionType {
    EQUAL,
    MORE_THAN,
    LESS_THAN,
}

@Entity
class ConditionNode : Node() {
    @Column(name = "condition_type")
    @NotNull
    @Enumerated
    var conditionType: ConditionType? = null

    @Column(name = "condition_value")
    @NotNull
    @NotEmpty
    var value: String = ""

    override fun update(incoming: String, repositoriesHolder: NodeRepositoriesHolder) {
        val conditionMet = when(conditionType) {
            ConditionType.EQUAL -> incoming == value
            ConditionType.MORE_THAN -> incoming.toFloat() > value.toFloat()
            ConditionType.LESS_THAN -> incoming.toFloat() < value.toFloat()
            else -> false
        }

        if (conditionMet) {
            this.conditionMet = true
            repositoriesHolder.conditionNodeRepository.save(this)
        }

        propagate(
            if (conditionMet) "1" else "0",
            repositoriesHolder
        )
    }
}

enum class OperatorType {
    AND,
    OR,
}

@Entity
class OperatorNode : Node() {
    @Column(name = "operator_type")
    @NotNull
    @Enumerated
    var operatorType: OperatorType? = null
    override fun update(incoming: String, repositoriesHolder: NodeRepositoriesHolder) {
        val predecessors = repositoriesHolder.conditionNodeRepository.findPredecessorsByNode(this)

        val validConditions = predecessors.count { it.conditionMet }
        val result = when(operatorType) {
            OperatorType.AND -> {
                if (validConditions == predecessors.size) {
                    this.conditionMet = true
                    repositoriesHolder.operatorNodeRepository.save(this)
                    "1"
                } else {
                    "0"
                }
            }

            OperatorType.OR -> {
                if (validConditions > 0) {
                    this.conditionMet = true
                    repositoriesHolder.operatorNodeRepository.save(this)
                    "1"
                } else {
                    "0"
                }
            }

            else -> "0"
        }

        propagate(result, repositoriesHolder)
    }
}

@Entity
class ActionNode : Node() {
    @NotNull
    @OneToOne
    val deviceAction: DeviceAction? = null


    @Column(name = "action_value")
    @NotNull
    @NotEmpty
    var value: String = ""

    override fun update(incoming: String, repositoriesHolder: NodeRepositoriesHolder) {
        val predecessors = repositoriesHolder.operatorNodeRepository.findPredecessorsByNode(this)
        val validConditions = predecessors.count { it.conditionMet }
        if (validConditions < predecessors.size) {
            return
        }

        deviceAction?.let {
            val statusUpdate = StatusUpdate(deviceAction.idDevice, value)
            val json = Json.encodeToString(StatusUpdate.serializer(), statusUpdate)
            deviceAction.device?.actionTopic?.let { it1 -> repositoriesHolder.broker.addToTopic(it1, json) }
        }
    }
}

@Entity
class EventNode : Node() {
    override fun update(incoming: String, repositoriesHolder: NodeRepositoriesHolder) {
        propagate(incoming, repositoriesHolder)
    }
}
