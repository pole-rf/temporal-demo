package rf.pole.demos.temporal.ms_starter.controller

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import rf.pole.demos.temporal.ms_starter.workflow.api.OtherWorkflow
import rf.pole.demos.temporal.ms_starter.workflow.api.SomeWorkflow
import java.util.UUID.randomUUID

@RestController
class Controller(
    @Qualifier("workflow-client-starter")
    private val workflowClient: WorkflowClient,
    private val ctx: ApplicationContext,
) {
    private val log = LoggerFactory.getLogger(Controller::class.java)!!

    @GetMapping("/workflow/some/start")
    fun startSomeWF(@RequestParam data: String): String{
        log.debug("Preparing SomeWorkflow with data=$data")

        val wf = workflowClient.newWorkflowStub(
            SomeWorkflow::class.java,
            WorkflowOptions.newBuilder().setTaskQueue("starter").setWorkflowId("some-workflow-${randomUUID()}").build()
        )

        log.debug("Starting SomeWorkflow")
        val execution = WorkflowClient.start(wf::execute, data)
        log.debug("SomeWorkflow started: ${execution.workflowId}/${execution.runId}")
        return "OK\n${execution.workflowId}/${execution.runId}"
    }

    @GetMapping("/workflow/start/other")
    fun startOtherWF(@RequestParam data: String): String{
        log.debug("Preparing OtherWorkflow with data=$data")

        val wf = workflowClient.newWorkflowStub(
            OtherWorkflow::class.java,
            WorkflowOptions.newBuilder().setTaskQueue("starter").setWorkflowId("other-workflow-${randomUUID()}").build()
        )

        log.debug("Starting OtherWorkflow")
        val execution = WorkflowClient.start(wf::execute, data)
        log.debug("OtherWorkflow started: ${execution.workflowId}/${execution.runId}")
        return "OK\n${execution.workflowId}/${execution.runId}"
    }
}