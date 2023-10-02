package com.ader.simplepayment.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/health")
    fun healthCheck(): String {
        return "OK"
    }

    @GetMapping("/health-coroutine")
    suspend fun healthCheckCoroutine(): String {
        return "OK - Coroutine"
    }
}
