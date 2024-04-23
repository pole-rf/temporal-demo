package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import rf.pole.demos.temporal.ms_starter.workflow.api.PeriodicJob10s
import java.time.Duration

@WorkflowImpl(workers = ["periodic-job-10s"], taskQueues = ["periodic-job-10s"])
class PeriodicJob10sImpl : PeriodicJob10s {
    val log = Workflow.getLogger(this::class.java)
    override fun execute() {
        log.info("Running periodic job 10s")
        Workflow.sleep(Duration.ofMinutes(3))
        log.info("Periodic job done 10s")
    }
}