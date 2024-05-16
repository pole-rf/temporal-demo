package rf.pole.demos.temporal.ms_pub_sub.activity.api

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface PublishEventA {
    @ActivityMethod(name = "Publish Event A")
    fun publish(eventSubType: String, data: String)
}