plugins {
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.openapi.generator") version "4.3.1"
    java
}

group = "com.github.joern.kalz"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.springfox:springfox-swagger2:2.8.0")
    implementation("org.openapitools:jackson-databind-nullable:0.1.0")
    implementation("javax.validation:validation-api")
    runtimeOnly(project(":client"))
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$rootDir/openapi.yaml")
    apiPackage.set("com.github.joern.kalz.openapi.spring.angular.starter.generated.api")
    modelPackage.set("com.github.joern.kalz.openapi.spring.angular.starter.generated.model")
    outputDir.set("$buildDir/generated/openapi")
    configOptions.set(mapOf(
        "interfaceOnly" to "true",
        "skipDefaultInterface" to "true"
    ))
}

tasks {
    named("compileJava").configure {
        dependsOn(named("openApiGenerate"))
    }
}

sourceSets {
    getByName("main") {
        java {
            srcDir("$buildDir/generated/openapi/src/main/java")
        }
    }
}