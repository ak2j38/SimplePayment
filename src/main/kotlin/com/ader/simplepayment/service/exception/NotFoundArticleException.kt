package com.ader.simplepayment.service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundArticleException(
    override val message: String?
): BusinessException()
