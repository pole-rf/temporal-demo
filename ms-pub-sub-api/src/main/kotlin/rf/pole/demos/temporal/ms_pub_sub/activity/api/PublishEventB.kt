package rf.pole.demos.temporal.ms_pub_sub.activity.api

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface PublishEventB {
    @ActivityMethod(name = "Publish Event B")
    fun publish(eventSubType: String, data: String)
}