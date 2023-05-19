package com.iota.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.iota.core.dto.device.RequiredProperties
import com.iota.core.model.Action
import jakarta.persistence.EntityManagerFactory
import org.hibernate.SessionFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig
@DataJpaTest
class ActionTests {

	@Autowired
	private lateinit var entityManagerFactory: EntityManagerFactory
	private lateinit var objectMapper: ObjectMapper

	@BeforeEach()
	fun setup() {
		objectMapper = ObjectMapper()
	}

	@Test
	fun testSaveAndRetrieveAction() {
		val action = Action();
		action.name = "test"

		val requiredProperties: RequiredProperties = mutableListOf();
		requiredProperties.add("min");
		requiredProperties.add("max");
		requiredProperties.add("step");
		action.required = requiredProperties;

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
}
