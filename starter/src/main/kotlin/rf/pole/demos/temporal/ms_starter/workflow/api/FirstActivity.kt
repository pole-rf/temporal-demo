package rf.pole.demos.temporal.ms_starter.workflow.api

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface FirstActivity {
    @ActivityMethod(name = "Подготовка сделки")
    fun doFirstAction(data: String, ext: String?): String?
}
