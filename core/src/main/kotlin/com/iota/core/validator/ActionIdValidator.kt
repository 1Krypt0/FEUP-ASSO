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
@Constraint(validatedBy = [ActionIdValidator::class])
annotation class ValidActionId(
    val message: String = "Invalid device action ID",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class ActionIdValidator() : ConstraintValidator<ValidActionId, Long?> {
    @Autowired
    var actionRepository: ActionRepository? = null

    constructor(actionRepository: ActionRepository?) : this() {
        this.actionRepository = actionRepository
    }

    override fun isValid(id: Long?, context: ConstraintValidatorContext): Boolean {
        return validate(id)
    }

    fun validate(id: Long?): Boolean {
        if (id == null) {
            return false
        }

        return actionRepository?.let { actionRepository!!.findActionById(id).isPresent } ?: false
    }
}