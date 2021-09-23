@file:Suppress("HardCodedStringLiteral")

val reactSuffix = "-pre.243-kotlin-1.5.30"
val kotlinReact = "17.0.2$reactSuffix"
val ktorVersion = "1.6.3"
val kotlinxSerializationVersion = "1.2.2"

plugins {
    kotlin("js") version "1.5.30"
    kotlin("plugin.serialization") version "1.5.30"
}

group = "com.camline.space.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:$kotlinReact")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:$kotlinReact")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.1$reactSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:5.2.0$reactSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.1$reactSuffix")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux:7.2.4$reactSuffix")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
}

kotlin {
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}