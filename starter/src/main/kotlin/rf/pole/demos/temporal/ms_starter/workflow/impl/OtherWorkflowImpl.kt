package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.activity.ActivityOptions
import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import rf.pole.demos.temporal.ms_pub_sub.activity.api.PublishEventB
import rf.pole.demos.temporal.ms_starter.workflow.api.FirstActivity
import rf.pole.demos.temporal.ms_starter.workflow.api.OtherWorkflow
import rf.pole.demos.temporal.ms_starter.workflow.api.SecondActivity
import java.time.Duration

@WorkflowImpl(workers = ["starter"], taskQueues = ["starter"])
open class OtherWorkflowImpl : OtherWorkflow {
    private val log = Workflow.getLogger(OtherWorkflowImpl::class.java)!!

    private val firstActivity = Workflow.newActivityStub(
        FirstActivity::class.java,
        ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build()
    )
    private val secondActivity = Workflow.newActivityStub(
        SecondActivity::class.java,
        ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build()
    )

    private val publishEventB = Workflow.newActivityStub(
        PublishEventB::class.java,
        ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build()
    )

    override fun execute(data: String) {
        log.debug("Starting SomeWorkflow execution")

        firstActivity.doFirstAction(data, "zzz")
        secondActivity.doSecondAction(data)

        publishEventB.publish("subType1", data)
    }
}
