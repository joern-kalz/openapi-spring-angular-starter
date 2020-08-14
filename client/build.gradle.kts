import com.moowork.gradle.node.npm.NpmTask

plugins {
    id("org.openapi.generator") version "4.3.1"
    id("com.github.node-gradle.node") version "2.2.4"
    java
}

openApiGenerate {
    generatorName.set("typescript-angular")
    inputSpec.set("$rootDir/openapi.yaml")
    outputDir.set("$projectDir/src/app/generated/openapi")
}

node {
    download = true
    version = "14.7.0"
    npmVersion = "6.14.7"
}

val npmBuild by tasks.registering(NpmTask::class) {
    dependsOn(tasks.named("openApiGenerate"))
    setArgs(listOf("run-script", "build", "--prod"))
}

tasks {
    named("jar", Jar::class).configure {
        dependsOn(npmBuild)
        from("dist/client")
        into("META-INF/resources/ui")
    }
}

