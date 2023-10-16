package com.ader.simplepayment.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
class AdvancedController {

  @GetMapping("/test/mdc")
  suspend fun testRequestTxid() {
    logger.debug { "Hello MDC" }
  }
}
