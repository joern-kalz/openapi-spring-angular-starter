import com.moowork.gradle.node.npm.NpmTask

plugins {
    id("com.github.node-gradle.node") version "2.2.4"
    java
}

node {
    download = true
    version = "14.7.0"
    npmVersion = "6.14.7"
}

val npmBuild by tasks.registering(NpmTask::class) {
    setArgs(listOf("run-script", "build", "--prod"))
}

tasks {
    named("jar", Jar::class).configure {
        dependsOn(npmBuild)
        from("dist/client")
        into("META-INF/resources/ui")
    }
}

