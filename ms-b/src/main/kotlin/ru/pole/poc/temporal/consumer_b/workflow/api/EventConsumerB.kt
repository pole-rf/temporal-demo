package ru.pole.poc.temporal.consumer_a.workflow.api

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface EventConsumerB {
    @WorkflowMethod
    fun event(entityId: String, newState: String)
}