package rf.pole.demos.temporal.ms_pub_sub.workflow

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface EventAWorkflow {
    @WorkflowMethod(name="EventAWorkflow")
    fun publishEvent(eventSubType: String, data: String)
}
