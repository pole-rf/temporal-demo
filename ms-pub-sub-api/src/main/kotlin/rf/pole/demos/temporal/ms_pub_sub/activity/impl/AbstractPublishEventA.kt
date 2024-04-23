package rf.pole.demos.temporal.ms_pub_sub.activity.impl

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.workflow.Workflow
import rf.pole.demos.temporal.ms_pub_sub.activity.api.PublishEventA
import rf.pole.demos.temporal.ms_pub_sub.workflow.EventAWorkflow
import java.util.UUID.randomUUID


//@ActivityImpl(workers = ["ms-pub-sub.pub"], taskQueues = ["ms-pub-sub.pub"])
abstract class AbstractPublishEventA(
    private val workflowClient: WorkflowClient
) : PublishEventA {
    private val log = Workflow.getLogger(this::class.java)!!

    override fun publish(eventSubType: String, data: String) {
//        require( workflowClient.options.namespace == "ms-pub-sub")
        log.debug("Starting PublishEventA wf")
        val publishWorkflow = workflowClient.newWorkflowStub(
            EventAWorkflow::class.java,
            WorkflowOptions.newBuilder().setTaskQueue("pub-sub").setWorkflowId("workflow-id-emit-event-a-${randomUUID()}").build()
        )
        WorkflowClient.start(publishWorkflow::publishEvent, eventSubType, data)
    }
}