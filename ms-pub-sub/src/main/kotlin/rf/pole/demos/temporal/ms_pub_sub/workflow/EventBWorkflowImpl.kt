package rf.pole.demos.temporal.ms_pub_sub.workflow

import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow


@WorkflowImpl(taskQueues = ["pub-sub"])
class EventBWorkflowImpl : EventBWorkflow {
    private val log = Workflow.getLogger(EventAWorkflowImpl::class.java)!!

    override fun publishEvent(eventSubType: String, data: String){
        log.debug("Event B published. eventSubType=$eventSubType, data=$data")
    }
}