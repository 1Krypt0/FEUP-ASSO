package com.iota.core.validator

import com.iota.core.dto.model.DeviceActionDto
import com.iota.core.model.Action
import com.iota.core.repository.ActionRepository
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
@Constraint(validatedBy = [DeviceActionPropertiesValidator::class])
annotation class ValidDeviceActionProperties(
    val message: String = "Properties do not match required properties",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class DeviceActionPropertiesValidator() : ConstraintValidator<ValidDeviceActionProperties, DeviceActionDto> {
    @Autowired
    var actionRepository: ActionRepository? = null

    constructor(actionRepository: ActionRepository?) : this() {
        this.actionRepository = actionRepository
    }

    override fun isValid(deviceAction: DeviceActionDto, context: ConstraintValidatorContext): Boolean {
        return validate(deviceAction)
    }

    fun validate(deviceAction: DeviceActionDto): Boolean {
        if (deviceAction.id == null) {
            return true
        }

        val actionOptional: Optional<Action>? = actionRepository?.findActionById(deviceAction.id)
        if (actionOptional == null || actionOptional.isEmpty) {
            return true
        }
        val action = actionOptional.get()


        val requiredProperties = action.required

        if (requiredProperties.isEmpty()) {
            return true
        } else if (deviceAction.properties.isEmpty()) {
            return false
        }

        return deviceAction.properties.keys.containsAll(requiredProperties)
    }
}