package ru.pole.poc.temporal.ms_integration_system1.workflow.impl

import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import io.temporal.workflow.WorkflowMethod
import ru.pole.poc.temporal.ms_integration_system1.workflow.api.EventConsumerA

@WorkflowImpl(taskQueues = ["sub-event-a"])
class EventConsumerAImpl : EventConsumerA {
    private val log = Workflow.getLogger(EventConsumerAImpl::class.java)!!

    @WorkflowMethod
    override fun event(eventSubType: String, data: String) {
        log.debug("Event A received. subtype=$eventSubType, data=$data")
    }
}