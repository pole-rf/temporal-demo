package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.client.WorkflowClient
import io.temporal.spring.boot.ActivityImpl
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import rf.pole.demos.temporal.ms_pub_sub.activity.impl.AbstractPublishEventA

@Component
@ActivityImpl(workers = ["starter"], taskQueues = ["starter"])
class PublishEventAImpl(@Qualifier("workflow-client-pub-sub") private val workflowClient: WorkflowClient) : AbstractPublishEventA(workflowClient)
