package rf.pole.demos.temporal.ms_integration_system2

import org.springframework.boot.autoconfigure.SpringBootApplication
import rf.pole.lib.core.runPoleMicroservice

@SpringBootApplication
class AppMsIntegrationSystem2

fun main(args: Array<String>) {
    runPoleMicroservice(AppMsIntegrationSystem2::class.java, args)
}
