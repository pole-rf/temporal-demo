package rf.pole.demos.temporal.ms_starter.workflow.api

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface FindMyIpWorkflow {
    @WorkflowMethod(name = "Получение текущего IP")
    fun findMyIp(flag: Boolean): String
}
