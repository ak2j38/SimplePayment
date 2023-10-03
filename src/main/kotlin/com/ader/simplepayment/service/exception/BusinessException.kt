package com.ader.simplepayment.service.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

open class BusinessException : RuntimeException {

    lateinit var httpStatus: HttpStatus

    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(httpStatus: HttpStatus, message: String) : super(message) {
        this.httpStatus = httpStatus
    }
}
