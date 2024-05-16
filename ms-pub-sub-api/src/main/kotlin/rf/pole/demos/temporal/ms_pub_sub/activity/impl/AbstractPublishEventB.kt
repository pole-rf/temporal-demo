package rf.pole.demos.temporal.ms_pub_sub.activity.impl

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.workflow.Workflow
import rf.pole.demos.temporal.ms_pub_sub.activity.api.PublishEventB
import rf.pole.demos.temporal.ms_pub_sub.workflow.EventBWorkflow
import java.util.UUID.randomUUID


abstract class AbstractPublishEventB(
    private val workflowClient: WorkflowClient
) : PublishEventB {
    private val log = Workflow.getLogger(this::class.java)!!

    override fun publish(eventSubType: String, data: String) {
//        require( workflowClient.options.namespace == "ms-pub-sub")
        log.debug("Starting PublishEventB wf")
        val publishWorkflow = workflowClient.newWorkflowStub(
            EventBWorkflow::class.java,
            WorkflowOptions.newBuilder().setTaskQueue("pub-sub").setWorkflowId("workflow-id-emit-event-b-${randomUUID()}").build()
        )
        WorkflowClient.start(publishWorkflow::publishEvent, eventSubType, data)
    }
}