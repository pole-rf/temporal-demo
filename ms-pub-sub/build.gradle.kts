plugins {
    alias(pole.plugins.kotlinJvm)
    alias(pole.plugins.kotlinAllOpen)
    alias(pole.plugins.kotlinSpring)

    alias(pole.plugins.springBoot)
    alias(pole.plugins.springDependencyManagement)
}

dependencies {
    implementation(project(":lib-core"))

    implementation(project(":ms-pub-sub-api"))
    implementation(project(":ms-integration-system1"))
    implementation(project(":ms-integration-system2"))

    // Pole-Parent Bundles - core - should be always enabled for all MicroServices
    implementation(pole.bundles.core)
    runtimeOnly(pole.bundles.coreRuntime)

    implementation(pole.bundles.temporal)
}

springBoot {
    mainClass = "rf.pole.demos.temporal.ms_pub_sub.AppMSPubSubKt"
}
