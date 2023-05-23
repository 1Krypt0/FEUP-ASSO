package com.iota.core.validator

import com.iota.core.repository.ActionRepository
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.springframework.beans.factory.annotation.Autowired
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [ActionNameValidator::class])
annotation class ValidActionName(
    val message: String = "Device action name is not valid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class ActionNameValidator() : ConstraintValidator<ValidActionName, String> {
    @Autowired
    var actionRepository: ActionRepository? = null

    constructor(actionRepository: ActionRepository?) : this() {
        this.actionRepository = actionRepository
    }

    override fun isValid(name: String, context: ConstraintValidatorContext): Boolean {
        return validate(name)
    }

    fun validate(name: String): Boolean {
        return actionRepository?.let { actionRepository!!.findByName(name).isPresent } ?: false
    }
}