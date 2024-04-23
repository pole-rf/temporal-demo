package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.spring.boot.ActivityImpl
import io.temporal.workflow.Workflow
import org.springframework.stereotype.Component
import rf.pole.demos.temporal.ms_starter.workflow.api.FirstActivity


@Component
@ActivityImpl(workers = ["starter"], taskQueues = ["starter"])
class FirstActivityImpl : FirstActivity {
    private val log = Workflow.getLogger(FirstActivityImpl::class.java)!!

    override fun doFirstAction(data: String) {
        println("doFirstAction() call")
        log.debug("doFirstAction() call")
    }
}