package rf.pole.demos.temporal.ms_integration_system2.workflow.impl

import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowMethod
import rf.pole.demos.temporal.ms_integration_system2.workflow.api.EventConsumer2

@WorkflowImpl(taskQueues = ["sub-event-2"])
class EventConsumer2Impl : EventConsumer2 {
    private val log = Workflow.getLogger(EventConsumer2Impl::class.java)!!

    @WorkflowMethod
    override fun event(eventSubType: String, data: String) {
        log.debug("Event 2 received. subtype=$eventSubType, data=$data")
    }
}