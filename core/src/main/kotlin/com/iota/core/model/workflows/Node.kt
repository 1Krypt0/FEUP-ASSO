package com.iota.core.model.workflows

import com.iota.core.model.DeviceAction
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotEmpty
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var successors: MutableSet<Node> = mutableSetOf()

    var conditionMet: Boolean = false
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
}

@Entity
class ActionNode : Node() {
    @NotNull
    @OneToOne
    var deviceAction: DeviceAction? = null


    @Column(name = "action_value")
    @NotNull
    @NotEmpty
    var value: String = ""
}

@Entity
class EventNode : Node() {
    @NotNull
    @OneToOne
    var deviceAction: DeviceAction? = null
}
