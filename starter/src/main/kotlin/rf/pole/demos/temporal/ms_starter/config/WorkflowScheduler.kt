package rf.pole.demos.temporal.ms_starter.config

import io.temporal.client.WorkflowOptions
import io.temporal.client.schedules.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import rf.pole.demos.temporal.ms_starter.workflow.api.CronJob
import rf.pole.demos.temporal.ms_starter.workflow.api.PeriodicJob10s
import rf.pole.demos.temporal.ms_starter.workflow.api.PeriodicJob5m
import java.util.*


@Component
class WorkflowScheduler : ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private lateinit var scheduleClient: ScheduleClient
    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        scheduleClient.listSchedules().forEach {
            scheduleClient.getHandle(it.scheduleId).delete()
        }
        val workflowOptions_5m =
            WorkflowOptions.newBuilder().setWorkflowId("periodic-job-5m-workflow-${UUID.randomUUID()}")
                .setTaskQueue("periodic-job-5m").build()

        val action_5m =
            ScheduleActionStartWorkflow.newBuilder()
                .setWorkflowType(PeriodicJob5m::class.java)
                .setOptions(workflowOptions_5m)
                .build()

        val schedule_5m =
            Schedule.newBuilder().setAction(action_5m).setSpec(
                ScheduleSpec.newBuilder().setIntervals(listOf(ScheduleIntervalSpec(java.time.Duration.ofMinutes(5))))
                    .build()
            ).build()
        scheduleClient.createSchedule(
            "periodic-job-schedule-5m-v1.1",
            schedule_5m,
            ScheduleOptions.newBuilder().build()
        )


        val workflowOptions_10s =
            WorkflowOptions.newBuilder().setWorkflowId("periodic-job-10s-workflow-${UUID.randomUUID()}")
                .setTaskQueue("periodic-job-10s").build()

        val action_10s =
            ScheduleActionStartWorkflow.newBuilder()
                .setWorkflowType(PeriodicJob10s::class.java)
                .setOptions(workflowOptions_10s)
                .build()

        val schedule_10s =
            Schedule.newBuilder().setAction(action_10s).setSpec(
                ScheduleSpec.newBuilder().setIntervals(listOf(ScheduleIntervalSpec(java.time.Duration.ofSeconds(10))))
                    .build()
            ).build()
        scheduleClient.createSchedule(
            "periodic-job-schedule-10s-v1.1",
            schedule_10s,
            ScheduleOptions.newBuilder().build()
        )


        val workflowOptions_cron = WorkflowOptions.newBuilder().setWorkflowId("cron-job-workflow-${UUID.randomUUID()}")
            .setTaskQueue("cron-job").build()

        val action_cron =
            ScheduleActionStartWorkflow.newBuilder()
                .setWorkflowType(CronJob::class.java)
                .setOptions(workflowOptions_cron)
                .build()

        val schedule_cron =
            Schedule.newBuilder().setAction(action_cron).setSpec(
                ScheduleSpec.newBuilder()
                    .setCalendars(
                        listOf(
                            ScheduleCalendarSpec.newBuilder()
                                .setHour(listOf(ScheduleRange(0, 23)))
                                .setMinutes(listOf(ScheduleRange(0, 59)))
                                .setSeconds(listOf(ScheduleRange(0, 59, 10)))
                                .build()
                        )
                    )
                    .build()
            ).build()
        scheduleClient.createSchedule("cron-job-schedule-v1.1", schedule_cron, ScheduleOptions.newBuilder().build())
    }
}