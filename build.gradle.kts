import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.farouk-abichou"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(libs.ffmpeg)
    implementation(libs.koin.core)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Kapture"
            packageVersion = "1.0.0"

            description = "AnnoDoc"
            copyright = "© 2023 Softylines. All rights reserved."
            vendor = "Softylines"
            licenseFile.set(project.file("LICENSE.txt"))

            appResourcesRootDir.set(project.layout.projectDirectory.dir("lib"))

            macOS {
                iconFile.set(project.file("logo.icns"))

                jvmArgs(
                    "-Xdock:name=Kapture",
                    "-Dapple.awt.application.appearance=system",
                )
            }

        }
    }
}
