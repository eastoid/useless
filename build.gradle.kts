import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    }

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.kwhat:jnativehook:2.2.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


tasks {
    withType<ShadowJar> {
        archiveFileName.set("mouseMoverShadow.jar")


        exclude("unwantedFile.txt")

        manifest {
            attributes["Main-Class"] = "mover.MouseMoverKt"
        }
    }
}





















//OLD GRADLE.BUILD



// import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// plugins {
//     kotlin("jvm") version "1.8.10"
// }

// group = "org.example"
// version = "1.0-SNAPSHOT"

// repositories {
//     mavenCentral()
// }

// dependencies {
//     implementation("com.github.kwhat:jnativehook:2.2.2")
//     testImplementation(kotlin("test"))
// }

// tasks.test {
//     useJUnitPlatform()
// }

// tasks.withType<KotlinCompile> {
//     kotlinOptions.jvmTarget = "1.8"
// }
