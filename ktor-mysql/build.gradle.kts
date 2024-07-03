
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project

val mysqlVersion:String by project
val koinKtor: String by project
val hikaricpVersion: String by project

plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
}

group = "example.com"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")

    // exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

    // mysql 드라이버
    implementation("mysql:mysql-connector-java:$mysqlVersion")

    // Koin for Ktor
    // 종속성 주입, 개발의 모듈성과 유연성을 향상
    implementation("io.insert-koin:koin-ktor:$koinKtor")

    // connection pooling
    // 연결 풀을 만들어 연결을 재사용하여 성능과 리소스를 최적화한다.
    implementation("com.zaxxer:HikariCP:$hikaricpVersion")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
