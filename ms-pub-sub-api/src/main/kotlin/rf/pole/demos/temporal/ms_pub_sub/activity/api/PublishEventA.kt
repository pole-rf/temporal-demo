package rf.pole.demos.temporal.ms_pub_sub.activity.api

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@ActivityInterface
interface PublishEventA {
    @ActivityMethod(name = "Publish Event A")
    fun publish(eventSubType: String, data: String)
}