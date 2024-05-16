package rf.pole.demos.temporal.ms_starter.config

import com.google.protobuf.util.Durations
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.temporal.api.workflowservice.v1.RegisterNamespaceRequest
import io.temporal.api.workflowservice.v1.RegisterNamespaceResponse
import io.temporal.serviceclient.WorkflowServiceStubs
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class TemporalNSMigration : ApplicationListener<ApplicationStartedEvent> {
    private val log = LoggerFactory.getLogger(TemporalNSMigration::class.java)

    private val workflowService = WorkflowServiceStubs.newLocalServiceStubs()

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        registerNamespace("ms-starter")
//        registerNamespace("ms-starter-periodic")
    }

    private fun registerNamespace(ns: String) {
        var resp: RegisterNamespaceResponse
        try {
            resp = workflowService.blockingStub().registerNamespace(
                RegisterNamespaceRequest.newBuilder()
                    .setNamespace(ns)
                    .setWorkflowExecutionRetentionPeriod(Durations.fromDays(30))
                    .build(),
            )
        } catch (e: StatusRuntimeException) {
            if (e.status.code == Status.Code.ALREADY_EXISTS) {
                log.debug("Namespace already exists")
                return
            } else {
                throw e
            }
        }
        log.debug("Namespace registered: {}", resp.isInitialized)
    }
}