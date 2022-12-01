import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    kotlin("jvm") version "1.7.22"
    id("com.diffplug.spotless") version "6.2.1"
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}

configure<SpotlessExtension> {
    kotlin {
        ktlint()
        trimTrailingWhitespace()
        endWithNewline()
        toggleOffOn()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
        endWithNewline()
        trimTrailingWhitespace()
    }
}
