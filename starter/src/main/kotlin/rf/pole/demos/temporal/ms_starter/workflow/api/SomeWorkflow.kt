package rf.pole.demos.temporal.ms_starter.workflow.api

import io.temporal.workflow.QueryMethod
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface SomeWorkflow {
    @WorkflowMethod(name="Some Workflow")
    fun execute(data: String)

    @QueryMethod
    fun query(): String

    @SignalMethod
    fun signal()
}