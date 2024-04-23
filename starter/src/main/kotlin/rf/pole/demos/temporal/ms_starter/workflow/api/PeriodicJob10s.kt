package rf.pole.demos.temporal.ms_starter.workflow.api

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface PeriodicJob10s {
    @WorkflowMethod
    fun execute()
}