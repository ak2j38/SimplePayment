package com.ader.simplepayment.controller.exception

import com.ader.simplepayment.service.exception.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [BusinessException::class])
    fun handleBusinessException(e: BusinessException): ResponseEntity<String> =
        ResponseEntity.status(e.httpStatus).body(e.message)
}
