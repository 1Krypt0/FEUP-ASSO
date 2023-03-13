package com.iota.core.dto

import org.springframework.validation.FieldError

class FieldErrorDto (
    val fieldName: String,
    val reason: String
)

class FieldErrorsDto (
    val message: String,
) {
    val fieldErrors: MutableList<FieldErrorDto> = ArrayList();
}