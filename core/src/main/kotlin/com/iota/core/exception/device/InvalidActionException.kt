package com.iota.core.exception.device

class InvalidActionException(
    val value: String,
) : Exception()

class ActionNotUpdatableException : Exception()
