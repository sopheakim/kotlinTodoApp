plugins {
    kotlin("jvm") version "1.9.0"
}

group = "com.todoapp"
version = "1.0-SNAPSHOT"

dependencies {
    // Kotlin standard library
    implementation(kotlin("stdlib"))

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("org.mockito:mockito-core:4.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnit()
    maxHeapSize = "1G"
}

tasks.withType<Test> {
    testLogging {
        events("passed", "skipped", "failed")
    }
}