package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import rf.pole.demos.temporal.ms_starter.workflow.api.FindMyIpWorkflow
import rf.pole.demos.temporal.ms_starter.workflow.api.RemoteApiCallActivity
import java.time.Duration

@WorkflowImpl(workers = ["starter"], taskQueues = ["starter"])
class FindMyIpWorkflowImpl : FindMyIpWorkflow {
    private val log = Workflow.getLogger(FindMyIpWorkflowImpl::class.java)

    private val ipifyService = Workflow.newActivityStub(
        RemoteApiCallActivity::class.java,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(10))
            .setRetryOptions(
                RetryOptions {
                    setInitialInterval(Duration.ofSeconds(3))
                    setBackoffCoefficient(1.2)
                    setMaximumAttempts(5)
//                    setDoNotRetry(RemoteApiCallNonRetriableException::class.simpleName)
                },
            )
            .build(),
    )

    override fun findMyIp(flag: Boolean): String {
        log.debug("Starting workflow")
        val result = ipifyService.getIp(flag)
        log.debug("IP found: {}", result)
        return result
    }
}
