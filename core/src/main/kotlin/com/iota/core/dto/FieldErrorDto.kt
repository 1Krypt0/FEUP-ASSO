package com.iota.core.dto

class FieldErrorDto(
    val fieldName: String,
    val reason: String
)

class FieldErrorsDto(
    val message: String,
) {
    val fieldErrors: MutableList<FieldErrorDto> = ArrayList()
}