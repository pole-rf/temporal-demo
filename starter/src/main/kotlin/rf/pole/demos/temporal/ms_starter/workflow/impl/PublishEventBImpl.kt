package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.client.WorkflowClient
import io.temporal.spring.boot.ActivityImpl
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import rf.pole.demos.temporal.ms_pub_sub.activity.impl.AbstractPublishEventB

@Component
@ActivityImpl(workers = ["starter"], taskQueues = ["starter"])
class PublishEventBImpl(@Qualifier("workflow-client-pub-sub") private val workflowClient: WorkflowClient) : AbstractPublishEventB(workflowClient)
