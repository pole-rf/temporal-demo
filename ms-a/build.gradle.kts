plugins {
    alias(pole.plugins.kotlinJvm)
    alias(pole.plugins.kotlinAllOpen)
    alias(pole.plugins.kotlinSpring)

    alias(pole.plugins.springBoot)
    alias(pole.plugins.springDependencyManagement)
}

dependencies {
    // Pole-Parent lib - core
    implementation(project(":lib-core"))

    // Pole-Parent Bundles - core - should be always enabled for all MicroServices
    implementation(pole.bundles.core)
    runtimeOnly(pole.bundles.coreRuntime)

    implementation(pole.bundles.temporal)
}

springBoot {
    mainClass = "rf.pole.demos.temporal.ms_b.AppMsAKt"
}
