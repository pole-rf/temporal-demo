plugins {
    alias(pole.plugins.kotlinJvm)
    alias(pole.plugins.kotlinAllOpen)
    alias(pole.plugins.kotlinJpa)
    alias(pole.plugins.springDependencyManagement)

    id("java-library")
    id("maven-publish")
}

dependencies {
    // Core Bundle always enabled for all MicroServices
    implementation(pole.bundles.core)
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
