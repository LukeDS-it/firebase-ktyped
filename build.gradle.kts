import com.jfrog.bintray.gradle.BintrayExtension
import org.ajoberstar.grgit.Grgit
import org.ajoberstar.grgit.service.BranchService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.4"
    id("org.ajoberstar.grgit") version "4.0.1"
    id("org.jetbrains.kotlin.js") version "1.3.61"
    id("com.github.node-gradle.node") version "2.2.1"
}

val cVersion = Version(0, 0, 1, true)

group = "it.ldsoftware"
version = cVersion.toString()

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
            name = project.name
            setLicenses("MIT")
            vcsUrl = "https://github.com/LukeDS-it/firebase-ktyped"
            publish = true
        })
        setPublications(project.name)
    }

}

data class Version(val major: Int, val minor: Int, val patch: Int, val snapshot: Boolean) {
    override fun toString(): String {
        val suffix = if (snapshot) {
            val branch = try {
                BranchService(Grgit.open(mapOf("currentDir" to project.rootDir)).repository).current()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            "-" + (branch?.name ?: "alpha") + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        }
        else ""

        return "$major.$minor.$patch$suffix"
    }
}
