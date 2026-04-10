plugins {
    kotlin("jvm") version "2.2.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.h2database:h2:2.4.240")
    testImplementation(kotlin("test"))
    runtimeOnly("com.h2database:h2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "org.mycompanyname.timerregister.TimerRegisterKt"
}