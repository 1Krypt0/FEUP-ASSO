package com.iota.core.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception


@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {
    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }

    @Override
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
}