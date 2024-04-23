package ru.pole.poc.temporal.consumer_a.workflow.impl

import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowMethod
import ru.pole.poc.temporal.consumer_a.workflow.api.EventConsumerB

@WorkflowImpl(taskQueues = ["sub-event-b"])
class EventConsumerBImpl : EventConsumerB {
    private val log = Workflow.getLogger(this::class.java)

    @WorkflowMethod
    override fun event(entityId: String, newState: String) {
        log.info("Event B received. entityId=$entityId / newState=$newState")

//        CollectRequiredInfo()
//        StoreCollectedInfoToDb()
//        GetYoungerStateChangesCount()
//        if (youngerChangeCount>0) {
//            WaitForYoungerStateChangesSignal()
//        }
//        CallDemetra1C()
//        DeleteCurrentRecordFromDb()
//        GetNextStateChangesWorkflowId()
//        SendSignalToNextStateChangesWorkflow()
    }
}
