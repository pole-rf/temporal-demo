package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import rf.pole.demos.temporal.ms_starter.workflow.api.PeriodicJob5m
import java.time.Duration

@WorkflowImpl(workers = ["periodic-job-5m"], taskQueues = ["periodic-job-5m"])
class PeriodicJob5mImpl : PeriodicJob5m {
    val log = Workflow.getLogger(this::class.java)
    override fun execute() {
        log.info("Running periodic job 5m")
        Workflow.sleep(Duration.ofMinutes(3))
        log.info("Periodic job done 5m")
    }
}