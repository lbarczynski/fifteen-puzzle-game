enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "fifteen-puzzle-game"

include(
    ":core:compose"
)

include(
    ":game:api",
    ":game:engine",
    ":game:ui",
)

include(
    ":feature:game"
)

include(
    ":app:shared",
    ":app:android"
)
