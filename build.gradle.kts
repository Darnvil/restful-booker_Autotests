plugins {
    kotlin("jvm") version "2.3.20"

    id("io.qameta.allure-report") version "4.0.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val aspectjAgent by configurations.creating

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    testImplementation("io.rest-assured:rest-assured:6.0.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.29.1")

    testImplementation("io.rest-assured:json-schema-validator:6.0.0")

    aspectjAgent("org.aspectj:aspectjweaver:1.9.22.1")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()

    jvmArgs("-javaagent:${aspectjAgent.singleFile}")

    val includeTags = System.getProperty("tags")
    if (includeTags != null) {
        useJUnitPlatform {
            includeTags(includeTags)
        }
    }

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

allure {
    version.set("2.29.0")
}