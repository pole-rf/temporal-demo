package ru.pole.poc.temporal.consumer_a.workflow.api

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface EventConsumerA {
    @WorkflowMethod
    fun event(eventSubType: String, data: String)
}