package com.ader.simplepayment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimplePaymentApplication

fun main(args: Array<String>) {
	runApplication<SimplePaymentApplication>(*args)
}
