package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Workflow
import rf.pole.demos.temporal.ms_starter.workflow.api.CronJob
import java.time.Duration

@WorkflowImpl(workers = ["cron-job"], taskQueues = ["cron-job"])
class CronJobImpl : CronJob {
    val log = Workflow.getLogger(this::class.java)
    override fun execute() {
        log.info("Running cron job")
        Workflow.sleep(Duration.ofMinutes(1))
        log.info("Cron job done")
    }
}