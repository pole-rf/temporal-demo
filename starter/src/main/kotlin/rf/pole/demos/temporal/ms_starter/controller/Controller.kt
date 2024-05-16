package rf.pole.demos.temporal.ms_starter.controller

import io.temporal.api.common.v1.WorkflowExecution
import io.temporal.api.enums.v1.WorkflowExecutionStatus
import io.temporal.api.workflowservice.v1.DescribeWorkflowExecutionRequest
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import rf.pole.demos.temporal.ms_starter.workflow.api.FindMyIpWorkflow
import rf.pole.demos.temporal.ms_starter.workflow.api.OtherWorkflow
import rf.pole.demos.temporal.ms_starter.workflow.api.SomeWorkflow
import java.util.*
import java.util.UUID.randomUUID

@RestController
class Controller(
    @Qualifier("workflow-client-starter")
    private val workflowClient: WorkflowClient,
    private val ctx: ApplicationContext,
) {
    private val log = LoggerFactory.getLogger(Controller::class.java)!!

    private val workflowService = WorkflowServiceStubs.newLocalServiceStubs()

    @GetMapping("/workflow/some/start")
    fun startSomeWF(@RequestParam data: String): String {
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

    @GetMapping("/workflow/other/start")
    fun startOtherWF(@RequestParam data: String): String {
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

    @GetMapping("/workflow/findip/start")
    fun startFindIp(@RequestParam flag: Boolean = false): Map<String, String> {
        log.debug("Preparing startFindIp")

        val wf = workflowClient.newWorkflowStub(
            FindMyIpWorkflow::class.java,
            WorkflowOptions.newBuilder()
                .setTaskQueue("starter")
                .setWorkflowId("findMyIp-${randomUUID()}")
                .build(),
        )

        log.debug("Starting FindMyIpWorkflow")
        val execution = WorkflowClient.start(wf::findMyIp, flag)
        log.debug("FindMyIpWorkflow started: ${execution.workflowId}/${execution.runId}")
        return mapOf("workflowId" to execution.workflowId, "runId" to execution.runId)
    }

    @GetMapping("/workflow/findip/get")
    fun getFindIpResult(@RequestParam wfId: String, @RequestParam wfRunId: String): String {
        log.debug("Preparing getFindIpResult")

        val wfStub = workflowClient.newUntypedWorkflowStub(wfId, Optional.of(wfRunId), Optional.empty())
        log.debug("Getting FindMyIpWorkflow result")
        val wfDescribeResp = workflowService.blockingStub().describeWorkflowExecution(
            DescribeWorkflowExecutionRequest.newBuilder()
                .setNamespace("ms-starter")
                .setExecution(
                    WorkflowExecution.newBuilder()
                        .setWorkflowId(wfId)
                        .setRunId(wfRunId),
                )
                .build(),
        )

        val wfStatus = wfDescribeResp.workflowExecutionInfo.status
        log.debug("FindMyIpWorkflow result: {}", wfStatus)

        val result = if (wfStatus == WorkflowExecutionStatus.WORKFLOW_EXECUTION_STATUS_COMPLETED) {
            wfStub.getResult(String::class.java)
        } else if (wfStatus == WorkflowExecutionStatus.WORKFLOW_EXECUTION_STATUS_RUNNING) {
            "Not ready yet"
        } else {
            "Error"
        }
        log.debug("getFindIpResult result={}", result)
        return result
    }
}