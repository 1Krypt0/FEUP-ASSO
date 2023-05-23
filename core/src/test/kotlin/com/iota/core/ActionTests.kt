package com.iota.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.iota.core.dto.device.Properties
import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action
import com.iota.core.model.Device
import com.iota.core.model.DeviceAction
import jakarta.persistence.EntityManagerFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig
@DataJpaTest
class ActionTests {

	@Autowired
	private lateinit var entityManagerFactory: EntityManagerFactory
	private lateinit var objectMapper: ObjectMapper

	@BeforeEach
	fun setup() {
		objectMapper = ObjectMapper()
	}

	@Test
	fun testSaveAndRetrieveAction() {
		val action = Action()
        action.name = "test123"
		action.type = "number"

		val requiredProperties: RequiredProperties = mutableListOf()
        requiredProperties.add("min")
        requiredProperties.add("max")
        requiredProperties.add("step")
        action.required = requiredProperties

        val entityManager = entityManagerFactory.createEntityManager()
		entityManager.transaction.begin()
		entityManager.persist(action)
		entityManager.transaction.commit()
		entityManager.close()

		val entityManager2 = entityManagerFactory.createEntityManager()
		val action2 = entityManager2.find(Action::class.java, action.id)

		assertEquals(action.name, action2.name)
		assertEquals(action.required, action2.required)
	}

	@Test
	fun testSaveAndRetrieveDeviceAction() {
		val deviceAction = DeviceAction()
        deviceAction.name = "name"
		deviceAction.displayName = "test"

		val properties: Properties = mutableMapOf()
        properties["min"] = "0"
		properties["max"] = "100"
		properties["step"] = "1"

		deviceAction.properties = properties
		deviceAction.status = "2"

		val entityManager = entityManagerFactory.createEntityManager()
		entityManager.transaction.begin()
		entityManager.persist(deviceAction)
		entityManager.transaction.commit()
		entityManager.close()

		val entityManager2 = entityManagerFactory.createEntityManager()
		val deviceAction2 = entityManager2.find(DeviceAction::class.java, deviceAction.id)

		assertEquals(deviceAction.displayName, deviceAction2.displayName)
		assertEquals(deviceAction.properties, deviceAction2.properties)
		assertEquals(deviceAction.status, deviceAction2.status)
	}

	@Test
	fun testSaveAndRetrieveManyToMany() {
		val device = Device()
        device.name = "test"
		device.macAddress = "123"

		val action = Action()
        action.name = "test"
		action.type = "number"
		val requiredProperties: RequiredProperties = mutableListOf()
        requiredProperties.add("min")
        requiredProperties.add("max")
        requiredProperties.add("step")
        action.required = requiredProperties

        val deviceAction = DeviceAction()
        deviceAction.displayName = "test"
		deviceAction.name = "name"

		val properties: Properties = mutableMapOf()
        properties["min"] = "0"
		properties["max"] = "100"
		properties["step"] = "1"

		deviceAction.properties = properties
		deviceAction.status = "2"

		deviceAction.device = device
		deviceAction.action = action

		val entityManager = entityManagerFactory.createEntityManager()
		entityManager.transaction.begin()
		entityManager.persist(device)
		entityManager.persist(action)
		entityManager.persist(deviceAction)
		entityManager.transaction.commit()
		entityManager.close()

		val entityManager2 = entityManagerFactory.createEntityManager()
		val deviceAction2 = entityManager2.find(DeviceAction::class.java, deviceAction.id)

		assertEquals(deviceAction.displayName, deviceAction2.displayName)
		assertEquals(deviceAction.properties, deviceAction2.properties)
		assertEquals(deviceAction.status, deviceAction2.status)
		assertEquals(deviceAction.device, deviceAction2.device)
		assertEquals(deviceAction.action, deviceAction2.action)
		assertEquals(deviceAction, deviceAction2.action?.deviceActions?.first())
		assertEquals(deviceAction, deviceAction2.device?.deviceActions?.first())
	}
}
