package rf.pole.demos.temporal.ms_starter.workflow.api

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface RemoteApiCallActivity {
    companion object {
        enum class Errors {
            DEFAULT_NON_RETRIABLE,
            DEFAULT_RETRIABLE,
        }
    }

    @ActivityMethod(name = "Вызов внешней службы")
    fun getIp(flag: Boolean): String
}
