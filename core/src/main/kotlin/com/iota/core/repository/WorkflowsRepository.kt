package com.iota.core.repository

import com.iota.core.model.workflows.ConditionNode
import com.iota.core.model.workflows.EventNode
import com.iota.core.model.workflows.Node
import com.iota.core.model.workflows.OperatorNode
import com.iota.core.model.workflows.Workflow
import com.iota.core.queue.Broker
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@NoRepositoryBean
interface NodeRepository<T : Node> : CrudRepository<T, Long> {
    @Query("select n from Node n where :node in elements(n.successors) ")
    fun findPredecessorsByNode(@Param("node") node: Node) : List<Node>
}

@Repository
interface ConditionNodeRepository : NodeRepository<ConditionNode> {
}

@Repository
interface OperatorNodeRepository : NodeRepository<OperatorNode>

@Repository
interface EventNodeRepository : NodeRepository<EventNode>