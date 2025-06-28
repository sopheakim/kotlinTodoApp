plugins {
    kotlin("jvm") version "1.9.0"
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    testImplementation(kotlin("test"))
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}