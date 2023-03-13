package com.iota.core.exception

import com.iota.core.dto.ErrorDto
import com.iota.core.exception.device.DeviceNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import kotlin.Exception


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
        if(statusCode.is5xxServerError) {
            LOGGER.error("A Server Error occurred with status {}", statusCode, ex);
        } else if(statusCode.is4xxClientError) {
            LOGGER.warn("A Client Error occurred with status {}", statusCode, ex);
        } else {
            LOGGER.warn("An exception has occurred with status {}", statusCode, ex);
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request)
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DeviceNotFoundException::class)
    @ResponseBody
    protected fun handleDeviceNotFound(ex: DeviceNotFoundException, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders();
        headers.contentType = MediaType.APPLICATION_JSON;

        val error = ErrorDto("Device %d was not found".format(ex.id));
        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(404), request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception::class)
    @ResponseBody
    protected fun handleAnyException(ex: Exception, request: WebRequest): ResponseEntity<Any>? {
        val headers = HttpHeaders();
        headers.contentType = MediaType.APPLICATION_JSON;

        val error = ErrorDto("An error occurred");
        return handleExceptionInternal(ex, error, headers, HttpStatusCode.valueOf(500), request);
    }
}