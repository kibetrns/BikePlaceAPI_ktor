val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_ktor_version: String by project
val koin_version: String by project
val kmongo_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.20"
    id("io.ktor.plugin") version "2.1.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20"

}

group = "ipsum_amet.me"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.cio.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-cio-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-serialization-gson-jvm:2.1.2")
    implementation("io.ktor:ktor-client-core-jvm:2.1.3")
    implementation("io.ktor:ktor-client-cio-jvm:2.1.3")
    implementation("io.ktor:ktor-client-serialization-jvm:2.1.3")
    implementation("io.ktor:ktor-client-auth-jvm:2.1.3")
    implementation("io.ktor:ktor-client-logging-jvm:2.1.3")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    //Ktor Client
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // Koin Core features
    implementation ("io.insert-koin:koin-core:$koin_version")
    // Koin Test features
    testImplementation ("io.insert-koin:koin-test:$koin_version")
    // Koin for Ktor
    implementation ("io.insert-koin:koin-ktor:$koin_ktor_version")
    // SLF4J Logger
    implementation ("io.insert-koin:koin-logger-slf4j:$koin_ktor_version")

    //Kotlinx-Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    //Kmongo
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongo_version")

    //Apache Commons Text
    implementation("org.apache.commons:commons-text:1.10.0")

    //Apache Commons Lang3
    implementation("org.apache.commons:commons-lang3:3.12.0")

}