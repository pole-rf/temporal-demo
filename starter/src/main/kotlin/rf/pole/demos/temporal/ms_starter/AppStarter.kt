package rf.pole.demos.temporal.ms_starter

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import rf.pole.lib.core.runPoleMicroservice

@SpringBootApplication
@ComponentScan(basePackages = ["rf.pole.demos"])
@AutoConfiguration
class AppStarter {
    @Bean("workflow-client-pub-sub")
    fun workflowClientPubSub(service: WorkflowServiceStubs): WorkflowClient {
        return WorkflowClient.newInstance(
            service,
            WorkflowClientOptions.newBuilder().setIdentity("AppPubSub Worker").setNamespace("ms-pub-sub").build(),
        )
    }

    @Bean("workflow-client-starter")
    fun workflowClientStarter(service: WorkflowServiceStubs): WorkflowClient {
        return WorkflowClient.newInstance(
            service,
            WorkflowClientOptions.newBuilder().setIdentity("AppStarter Worker").setNamespace("ms-starter").build(),
        )
    }
}

fun main(args: Array<String>) {
    runPoleMicroservice(AppStarter::class.java, args)
}
