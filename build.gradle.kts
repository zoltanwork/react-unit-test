@file:Suppress("HardCodedStringLiteral")

val reactSuffix = "-pre.256-kotlin-1.5.31"
val kotlinReact = "17.0.2$reactSuffix"
val ktorVersion = "1.6.4"
val kotlinxSerializationVersion = "1.3.0"

//val chartsKtMavenRepoUser: String? by project
//val chartsKtMavenRepoPassword: String? by project
//
//val d2vVersion = "0.8.15"
//val chartsKtVersion = "1.0.7-RC2"


plugins {
    kotlin("js") version "1.5.30"
    kotlin("plugin.serialization") version "1.5.30"
}

group = "com.camline.space.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
//    maven { setUrl("https://maven.pkg.jetbrains.space/data2viz/p/maven/public") }
//    maven {
//        url = uri("https://maven.pkg.jetbrains.space/data2viz/p/charts-1-r/maven")
//        authentication {
//            create<BasicAuthentication>("basic")
//        }
//        credentials {
//            username = chartsKtMavenRepoUser ?: System.getenv("chartsKtMavenRepoUser")
//            password = chartsKtMavenRepoPassword ?: System.getenv("chartsKtMavenRepoPassword")
//        }
//    }
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

//    implementation ("io.data2viz.d2v:viz:$d2vVersion")
//    implementation ("io.data2viz.charts:core:$chartsKtVersion")

    testImplementation(kotlin("test-js"))
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