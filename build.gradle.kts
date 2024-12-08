import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.detekt)
}

repositories {
    mavenCentral()
}

allprojects {
    afterEvaluate {
        apply(plugin = libs.plugins.detekt.get().pluginId)

        detekt {
            buildUponDefaultConfig = true
            autoCorrect = true
            config.from("$rootDir/config/detekt.yaml")
        }

        dependencies {
            detektPlugins(libs.detekt.plugins.formatting)
        }

        tasks.withType<Detekt>().configureEach {
            jvmTarget = JvmTarget.JVM_1_8.target
        }
    }
}

tasks.register<Detekt>("detektAll") {
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
    config.from("$rootDir/config/detekt.yaml")
    setSource(files(rootProject.projectDir))
}
