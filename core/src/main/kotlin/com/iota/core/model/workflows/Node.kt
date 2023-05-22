package com.iota.core.model.workflows

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var successors: MutableSet<Node> = mutableSetOf()

    var ready: Boolean = false

    protected fun commonUpdate(value: String) {
        successors.forEach { it.commonUpdate(value) }
    }

    abstract fun update(value: String)
}

enum class ConditionType {
    EQUAL,
    MORE_THAN,
    LESS_THAN,
    MORE_OR_EQUAL_THAN,
    LESS_OR_EQUAL_THAN,
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

    override fun update(value: String) {
        commonUpdate(value)
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
    override fun update(value: String) {
        commonUpdate(value)
    }
}

@Entity
class EventNode : Node() {
    override fun update(value: String) {
        commonUpdate(value)
    }
}
