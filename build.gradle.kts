import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(pole.plugins.kotlinJvm) apply false
    alias(pole.plugins.kotlinAllOpen) apply false
    alias(pole.plugins.kotlinSpring) apply false
    alias(pole.plugins.kotlinJpa) apply false

    alias(pole.plugins.springBoot) apply false
    alias(pole.plugins.springDependencyManagement) apply false
}

subprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }
}