plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.53.0"
    id("com.diffplug.spotless") version "8.1.0"
    id("org.sonarqube") version "7.1.0.6387"

//    application
    checkstyle
    `java-library`
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

sonar {
    properties {
        property("sonar.projectKey", "rychkov_java-project-78")
        property("sonar.organization", "rychkov")

        // Путь к XML отчету JaCoCo
        property("sonar.coverage.jacoco.xmlReportPaths",
            "${project.layout.buildDirectory.get()}/reports/jacoco/test/jacocoTestReport.xml")
        // Перечисляем пути к отчетам через запятую
        property("sonar.java.checkstyle.reportPaths",
            "${project.layout.buildDirectory.get()}/reports/checkstyle/main.xml")
    }
}

checkstyle {
    toolVersion = "10.12.4"
    // Игнорировать ошибки, чтобы сборка не падала сразу (на ваше усмотрение)
    isIgnoreFailures = false
    // Показывать ошибки в консоли
    isShowViolations = true
}

tasks.named<JavaCompile>("compileJava") {
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    outputs.upToDateWhen { false }

    finalizedBy(tasks.jacocoTestReport)
}

jacoco {
    toolVersion = "0.8.11"
}
tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}

tasks.named("sonar") {
    dependsOn(tasks.test)
    dependsOn(tasks.jacocoTestReport)
    dependsOn("checkstyleMain")
}

dependencies {
//    implementation("info.picocli:picocli:4.7.7")
//    annotationProcessor("info.picocli:picocli-codegen:4.7.7")
//    implementation("com.fasterxml.jackson.core:jackson-databind:2.21.0")
//    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.21.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}