package rf.pole.demos.temporal.ms_starter.workflow.api

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface SecondActivity {
    @ActivityMethod(name = "Создание первого этапа сделки")
    fun doSecondAction(data: String)
}
