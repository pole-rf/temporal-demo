package rf.pole.demos.temporal.ms_starter.config

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


@Configuration
class KtorClientConfig {
    @Bean(destroyMethod = "close")
    fun ktorHttpClient(): HttpClient {
        return HttpClient(CIO) {
            engine {
                requestTimeout = Duration.ofSeconds(3).toMillis()
                endpoint {
                    connectTimeout = Duration.ofSeconds(3).toMillis()
                }
            }
        }
    }
}
