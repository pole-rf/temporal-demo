package rf.pole.demos.temporal.ms_integration_system2.workflow.api

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface EventConsumer2 {
    @WorkflowMethod
    fun event(eventSubType: String, data: String)
}