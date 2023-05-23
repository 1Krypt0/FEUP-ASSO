package com.iota.core.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.stream.Collectors
import java.util.stream.Stream

class ValueOfEnumValidator : ConstraintValidator<ValueOfEnum, CharSequence?> {
    private var acceptedValues: List<String>? = null

    override fun initialize(annotation: ValueOfEnum) {
        acceptedValues = Stream.of(*annotation.enumClass.java.enumConstants)
            .map { obj: Enum<*> -> obj.name }
            .collect(Collectors.toList())
    }

    override fun isValid(value: CharSequence?, context: ConstraintValidatorContext): Boolean {
        return if (value == null) {
            true
        } else acceptedValues!!.contains(value.toString())
    }
}