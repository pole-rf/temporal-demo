rootProject.name = "temporal-demo"

include("lib-core")
include("starter")
include("ms-pub-sub")
include("ms-pub-sub-api")
include("ms-integration-system1")
include("ms-integration-system2")
include("ms-a")
include("ms-b")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("pole") {
            from(files("./gradle/pole.versions.toml"))
        }
    }
}
