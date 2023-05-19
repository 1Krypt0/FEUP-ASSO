package com.iota.core

import com.iota.core.dto.model.DeviceActionDto
import com.iota.core.model.Action
import com.iota.core.model.DeviceAction
import com.iota.core.repository.ActionRepository
import com.iota.core.validator.ActionIdValidator
import com.iota.core.validator.DeviceActionPropertiesValidator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.validation.MapBindingResult
import java.util.*


@SpringJUnitConfig
@ExtendWith(MockitoExtension::class)
class DeviceDtoTests {
    private lateinit var propertiesValidator: DeviceActionPropertiesValidator
    private lateinit var actionIdValidator: ActionIdValidator
    private lateinit var action : Action

    @MockBean
    private lateinit var actionRepository: ActionRepository

    @BeforeEach
    fun setup() {
        action = Action()
        action.id = 1
        action.name = "test"
        action.required = mutableListOf("min", "max", "step")

        `when`(actionRepository.findActionById(1)).thenReturn(Optional.of(action))

        propertiesValidator = DeviceActionPropertiesValidator(actionRepository)
        actionIdValidator = ActionIdValidator(actionRepository)
    }

    @Test
    fun testValidDeviceActionDto() {
        val dto = DeviceActionDto().apply {
            id = 1
            properties = mutableMapOf("min" to "0", "max" to "100", "step" to "1")
            name = "test"
            status = "2"
        }

        assertTrue(actionIdValidator.validate(dto.id));
        assertTrue(propertiesValidator.validate(dto));
    }

    @Test
    fun testInvalidDeviceActionIdDto() {
        val dto = DeviceActionDto().apply {
            id = 2
            properties = mutableMapOf("min" to "0", "max" to "100", "step" to "1")
            name = "test"
            status = "2"
        }

        assertFalse(actionIdValidator.validate(dto.id));
        assertTrue(propertiesValidator.validate(dto));
    }

    @Test
    fun testInvalidDeviceActionDtoProperties() {
        val dto = DeviceActionDto().apply {
            id = 1
            properties = mutableMapOf("min" to "0", "max" to "100")
            name = "test"
            status = "2"
        }

        assertTrue(actionIdValidator.validate(dto.id));
        assertFalse(propertiesValidator.validate(dto));
    }
}