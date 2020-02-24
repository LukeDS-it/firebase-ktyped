import com.jfrog.bintray.gradle.BintrayExtension
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.4"
    id("org.jetbrains.kotlin.js") version "1.3.61"
    id("net.researchgate.release") version "2.6.0"
    id("com.github.node-gradle.node") version "2.2.1"
}

group = "it.ldsoftware"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}

tasks {
    compileKotlinJs {
        kotlinOptions {
            outputFile = "build/classes/main/firebase-proxies.js"
            moduleKind = "commonjs"
            sourceMap = true
            sourceMapEmbedSources = "always"
        }
    }

    publishing {
        publications {
            register(project.name, MavenPublication::class) {
                from(getComponents()["kotlin"])
            }
        }
    }

    bintray {
        user = System.getenv("BINTRAY_USER")
        key = System.getenv("BINTRAY_PASS")
        pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
            repo = "maven"
            name = if (isSnapshot()) "${project.name}-branches" else project.name
            setLicenses("MIT")
            vcsUrl = "https://github.com/LukeDS-it/firebase-ktyped"
            publish = true
            version(delegateClosureOf<BintrayExtension.VersionConfig> {
                name = if (isSnapshot()) snapshotVersion() else project.version.toString()
            })
        })
        setPublications(project.name)
    }

    release {
        failOnSnapshotDependencies = false
        newVersionCommitMessage = "[ci skip] automatic versioning"
    }
}

gradle.taskGraph.whenReady {
    if (isSnapshot())
        version = snapshotVersion()
}

fun isSnapshot(): Boolean = (System.getenv("BRANCH") ?: "alpha") != "master"

fun snapshotVersion(): String {
    val current = """(\d*\.\d*\.\d*)""".toRegex().find(project.version.toString())!!.groups[0]!!.value
    val branch = (System.getenv("BRANCH") ?: "alpha")
    val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
    return "$current-$branch-$date"
}
