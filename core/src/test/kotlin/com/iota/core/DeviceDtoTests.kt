package com.iota.core

import com.iota.core.dto.model.DeviceActionDto
import com.iota.core.model.Action
import com.iota.core.repository.ActionRepository
import com.iota.core.validator.ActionNameValidator
import com.iota.core.validator.DeviceActionPropertiesValidator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.util.*


@SpringJUnitConfig
@ExtendWith(MockitoExtension::class)
class DeviceDtoTests {
    private lateinit var propertiesValidator: DeviceActionPropertiesValidator
    private lateinit var actionIdValidator: ActionNameValidator
    private lateinit var action : Action

    @MockBean
    private lateinit var actionRepository: ActionRepository

    @BeforeEach
    fun setup() {
        action = Action()
        action.id = 1
        action.name = "test"
        action.required = mutableListOf("min", "max", "step")

        `when`(actionRepository.findByName("test")).thenReturn(Optional.of(action))

        propertiesValidator = DeviceActionPropertiesValidator(actionRepository)
        actionIdValidator = ActionNameValidator(actionRepository)
    }

    @Test
    fun testValidDeviceActionDto() {
        val dto = DeviceActionDto().apply {
            actionName = "test"
            properties = mutableMapOf("min" to "0", "max" to "100", "step" to "1")
            name = "test"
            status = "2"
        }

        assertTrue(actionIdValidator.validate(dto.actionName));
        assertTrue(propertiesValidator.validate(dto));
    }

    @Test
    fun testInvalidDeviceActionIdDto() {
        val dto = DeviceActionDto().apply {
            actionName = "tests"

            properties = mutableMapOf("min" to "0", "max" to "100", "step" to "1")
            name = "tests"
            status = "2"
        }

        assertFalse(actionIdValidator.validate(dto.actionName));
        assertTrue(propertiesValidator.validate(dto));
    }

    @Test
    fun testInvalidDeviceActionDtoProperties() {
        val dto = DeviceActionDto().apply {
            actionName = "test"

            properties = mutableMapOf("min" to "0", "max" to "100")
            name = "test"
            status = "2"
        }

        assertTrue(actionIdValidator.validate(dto.actionName));
        assertFalse(propertiesValidator.validate(dto));
    }
}