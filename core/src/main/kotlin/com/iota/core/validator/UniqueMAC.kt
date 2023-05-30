package com.iota.core.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FIELD,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPE,
    AnnotationTarget.TYPE_PARAMETER
)
@Retention(
    AnnotationRetention.RUNTIME
)
@MustBeDocumented
@Constraint(validatedBy = [UniqueMACValidator::class])
annotation class UniqueMAC(
    val message: String = "already registered",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
