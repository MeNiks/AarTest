import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://jcenter.bintray.com")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://maven.google.com")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    }

}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.fabric.io/public")
        flatDir {
            dirs = mutableSetOf(File("libs"))
        }
        maven(url = "https://jcenter.bintray.com")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://maven.google.com")
    }
}