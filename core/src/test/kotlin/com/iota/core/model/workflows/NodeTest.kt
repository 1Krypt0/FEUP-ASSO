package com.iota.core.model.workflows

import com.iota.core.queue.Broker
import com.iota.core.repository.ConditionNodeRepository
import com.iota.core.repository.EventNodeRepository
import com.iota.core.repository.NodeRepositoriesHolder
import com.iota.core.repository.OperatorNodeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class NodeTest{

    @Autowired
    private lateinit var operatorNodeRepository: OperatorNodeRepository
    @Autowired
    private lateinit var conditionNodeRepository: ConditionNodeRepository
    @Autowired
    private lateinit var eventNodeRepository: EventNodeRepository

    @BeforeEach
    fun resetDB() {
        operatorNodeRepository.deleteAll()
        conditionNodeRepository.deleteAll()
        eventNodeRepository.deleteAll()
    }

    @Test
    fun testCreate() {
        val firstNode = ConditionNode()
        firstNode.value = "1"
        firstNode.conditionType = ConditionType.EQUAL
        conditionNodeRepository.save(firstNode)

        val secondNode = OperatorNode()
        secondNode.operatorType = OperatorType.AND
        operatorNodeRepository.save(secondNode)

        val eventNode = EventNode()
        eventNode.successors = mutableSetOf(firstNode, secondNode)
        eventNodeRepository.save(eventNode)

        assertEquals(1, eventNodeRepository.count())
        assertEquals(2, eventNodeRepository.findAll().toList()[0].successors.count())
    }

    @Test
    fun testPredecessors() {
        val mergeNode = OperatorNode()
        mergeNode.operatorType = OperatorType.AND
        operatorNodeRepository.save(mergeNode)


        val firstNode = ConditionNode()
        firstNode.value = "1"
        firstNode.successors = mutableSetOf(mergeNode)
        firstNode.conditionType = ConditionType.EQUAL
        conditionNodeRepository.save(firstNode)


        val secondNode = ConditionNode()
        secondNode.value = "100"
        secondNode.successors = mutableSetOf(mergeNode)
        secondNode.conditionType = ConditionType.MORE_THAN
        conditionNodeRepository.save(secondNode)


        val eventNode = EventNode()
        eventNode.successors = mutableSetOf(firstNode)
        eventNodeRepository.save(eventNode)

        val eventNode2 = EventNode()
        eventNode2.successors = mutableSetOf(secondNode)
        eventNodeRepository.save(eventNode2)

        assertEquals(1, conditionNodeRepository.findPredecessorsByNode(firstNode).count())
        assertEquals(1, conditionNodeRepository.findPredecessorsByNode(secondNode).count())
        assertEquals(2, conditionNodeRepository.findPredecessorsByNode(mergeNode).count())
    }

    @Test
    fun testUpdates() {
        val broker = Mockito.mock(Broker::class.java)

        val holder = NodeRepositoriesHolder(
            conditionNodeRepository,
            operatorNodeRepository,
            eventNodeRepository,
            broker
        )

        val mergeNode = OperatorNode()
        mergeNode.operatorType = OperatorType.AND
        operatorNodeRepository.save(mergeNode)


        val firstNode = ConditionNode()
        firstNode.value = "1"
        firstNode.successors = mutableSetOf(mergeNode)
        firstNode.conditionType = ConditionType.EQUAL
        conditionNodeRepository.save(firstNode)


        val secondNode = ConditionNode()
        secondNode.value = "100"
        secondNode.successors = mutableSetOf(mergeNode)
        secondNode.conditionType = ConditionType.MORE_THAN
        conditionNodeRepository.save(secondNode)


        val eventNode = EventNode()
        eventNode.successors = mutableSetOf(firstNode)
        eventNodeRepository.save(eventNode)

        val eventNode2 = EventNode()
        eventNode2.successors = mutableSetOf(secondNode)
        eventNodeRepository.save(eventNode2)


        eventNode.update("0", holder)
        assertFalse(firstNode.conditionMet)

        eventNode.update("1", holder)
        assertTrue(firstNode.conditionMet)

        assertFalse(mergeNode.conditionMet)

        eventNode2.update("101", holder)
        assertTrue(secondNode.conditionMet)
        assertTrue(mergeNode.conditionMet)


    }
}