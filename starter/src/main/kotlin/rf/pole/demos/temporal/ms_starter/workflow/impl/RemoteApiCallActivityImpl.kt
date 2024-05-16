package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.temporal.failure.ApplicationFailure
import io.temporal.spring.boot.ActivityImpl
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import rf.pole.demos.temporal.ms_starter.workflow.api.RemoteApiCallActivity

@Service
@ActivityImpl(workers = ["starter"], taskQueues = ["starter"])
class RemoteApiCallActivityImpl(
    val ktorHttpClient: HttpClient,
) : RemoteApiCallActivity {
    private val log = LoggerFactory.getLogger(RemoteApiCallActivity::class.java)

    override fun getIp(flag: Boolean): String {
        log.debug("Starting activity")
        val result = runBlocking {
            val response = ktorHttpClient.get(
                if (flag) {
                    "https://httpbin.org/status/403"
                } else {
                    "https://api.ipify.org?format=text"
                },
            )

            response.status.let { status ->
                if (status != HttpStatusCode.OK) {
                    if (status == HttpStatusCode.BadRequest || status == HttpStatusCode.Forbidden) {
                        throw ApplicationFailure.newNonRetryableFailure(
                            "Ошибка получения IP: $status ${response.bodyAsText()}",
                            RemoteApiCallActivity.Companion.Errors.DEFAULT_NON_RETRIABLE.name,
                        )
                    } else {
                        throw throw ApplicationFailure.newFailure(
                            "Ошибка получения IP: $status ${response.bodyAsText()}",
                            RemoteApiCallActivity.Companion.Errors.DEFAULT_RETRIABLE.name,
                        )
                    }
                }
            }

            response.bodyAsText()
        }
        log.debug("Activity finished: {}", result)
        return result
    }
}
