import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath(BuildsPlugin.androidBuildTool)
        classpath(BuildsPlugin.kotlinGradle)
        classpath(BuildsPlugin.navigationSafeArgGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven {url = uri("https://jitpack.io") }
    }
}

tasks.register("clean").configure {
    delete("build")
}