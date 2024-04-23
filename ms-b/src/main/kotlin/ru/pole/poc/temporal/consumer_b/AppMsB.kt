package ru.pole.poc.temporal.consumer_b

import org.springframework.boot.autoconfigure.SpringBootApplication
import rf.pole.lib.core.runPoleMicroservice

@SpringBootApplication
class AppMsB

fun main(args: Array<String>) {
    runPoleMicroservice(AppMsB::class.java, args)
}
