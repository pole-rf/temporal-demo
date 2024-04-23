package ru.pole.poc.temporal.ms_integration_system1

import org.springframework.boot.autoconfigure.SpringBootApplication
import rf.pole.lib.core.runPoleMicroservice

@SpringBootApplication
class AppMsIntegrationSystem1

fun main(args: Array<String>) {
    runPoleMicroservice(AppMsIntegrationSystem1::class.java, args)
}
