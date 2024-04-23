package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.spring.boot.ActivityImpl
import io.temporal.workflow.Workflow
import org.springframework.stereotype.Component
import rf.pole.demos.temporal.ms_starter.workflow.api.SecondActivity

@Component
@ActivityImpl(workers = ["starter"], taskQueues = ["starter"])
class SecondActivityImpl : SecondActivity {
    private val log = Workflow.getLogger(SecondActivityImpl::class.java)!!

    override fun doSecondAction(data: String) {
        println("doSecondAction() call")
        log.debug("doSecondAction() call")
    }
}