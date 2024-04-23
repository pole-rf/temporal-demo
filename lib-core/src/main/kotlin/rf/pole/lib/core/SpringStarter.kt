package rf.pole.lib.core

import mu.KotlinLogging
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.boot.SpringApplication
import org.springframework.core.io.ClassPathResource


fun <T> runPoleMicroservice(
    appClass: Class<T>,
    args: Array<String>,
) {
    val log = KotlinLogging.logger {}

    // While we have only two options - k8s or standalone - such simple if is enough for us
    val defaultYamlLocation = "rf/pole/lib/core/spring/application-default.yaml"

    // Load resource from classpath
    val yamlDefaultResource = ClassPathResource(defaultYamlLocation)

    // Create YAML factory which will load and parse our yaml file
    val factory = YamlPropertiesFactoryBean()
    factory.setResources(yamlDefaultResource)
    val defaultProperties = factory.getObject()

    // Create spring application and set properties default values from loaded yaml file
    val app = SpringApplication(appClass)
    app.setDefaultProperties(defaultProperties)
    app.setLogStartupInfo(true)
    val appCtx = app.run(*args)

    val appName = appCtx.environment.getProperty("spring.application.name")

    // Logging env name where current app was started
    log.info("Pole.rf Microservice ($appName) started")
    log.info("Default values loaded from $defaultYamlLocation file")
}
