package com.iota.core.exception

import com.iota.core.dto.ErrorDto
import com.iota.core.dto.FieldErrorDto
import com.iota.core.dto.FieldErrorsDto
import com.iota.core.exception.device.ActionNameNotFoundException
import com.iota.core.exception.device.ActionNotFoundException
import com.iota.core.exception.device.ActionNotUpdatableException
import com.iota.core.exception.device.DeviceNotFoundException
import com.iota.core.exception.device.InvalidActionException
import com.iota.core.exception.device.MACAlreadyRegistered
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {
    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }

    @Override
    @ResponseBody
    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        if (statusCode.is5xxServerError) {
            LOGGER.error("A Server Error occurred with status {}", statusCode, ex)
        } else if (statusCode.is4xxClientError) {
            LOGGER.warn("A Client Error occurred with status {}", statusCode, ex)
        } else {
            LOGGER.warn("An exception has occurred with status {}", statusCode, ex)
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request)
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DeviceNotFoundException::class)
    @ResponseBody
    protected fun handleDeviceNotFound(ex: DeviceNotFoundException, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val error = ErrorDto("device %d was not found".format(ex.id))
        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(404), request)
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ActionNotFoundException::class)
    @ResponseBody
    protected fun handleActionNotFound(ex: ActionNotFoundException, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val error = ErrorDto("action %d was not found".format(ex.id))
        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(404), request)
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidActionException::class)
    @ResponseBody
    protected fun handleActionNotFound(ex: InvalidActionException, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val error = ErrorDto("action does not allow ${ex.value}")
        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(422), request)
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ActionNotUpdatableException::class)
    @ResponseBody
    protected fun handleActionNotFound(ex: ActionNotUpdatableException, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val error = ErrorDto("action does not allow updates")
        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(422), request)
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ActionNameNotFoundException::class)
    @ResponseBody
    protected fun handleActionNameNotFound(ex: ActionNameNotFoundException, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val error = ErrorDto("action with name %d was not found".format(ex.name))
        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(404), request)
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MACAlreadyRegistered::class)
    @ResponseBody
    protected fun handleMacAlreadyRegistered(ex: MACAlreadyRegistered, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val error = FieldErrorsDto("arguments not valid")
        error.fieldErrors.add(FieldErrorDto(ex.field, ex.message ?: "already registered"))

        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(400), request)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val result = ex.bindingResult
        val error = FieldErrorsDto("arguments not valid")

        for (fieldError in result.fieldErrors) {
            error.fieldErrors.add(FieldErrorDto(fieldError.field, fieldError.defaultMessage ?: ""))
        }

        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(400), request)
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception::class)
    @ResponseBody
    protected fun handleAnyException(ex: Exception, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val error = ErrorDto("an error occurred")
        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(500), request)
    }
}