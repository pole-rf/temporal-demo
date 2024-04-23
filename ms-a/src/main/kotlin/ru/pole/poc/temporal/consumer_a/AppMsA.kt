package ru.pole.poc.temporal.consumer_a

import org.springframework.boot.autoconfigure.SpringBootApplication
import rf.pole.lib.core.runPoleMicroservice

@SpringBootApplication
class AppMsA

fun main(args: Array<String>) {
    runPoleMicroservice(AppMsA::class.java, args)
}
