package rf.pole.demos.temporal.ms_pub_sub

import org.springframework.boot.autoconfigure.SpringBootApplication
import rf.pole.lib.core.runPoleMicroservice

@SpringBootApplication
class AppMSPubSub

fun main(args: Array<String>) {
    runPoleMicroservice(AppMSPubSub::class.java, args)
}
