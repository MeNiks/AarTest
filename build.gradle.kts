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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("com.google.gms:google-services:4.3.3")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.2.0")
        classpath("com.android.tools.build.jetifier:jetifier-processor:1.0.0-beta09")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:9.0.0")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.16")
        classpath("org.jacoco:org.jacoco.core:0.8.5")
        classpath("org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlinVersion}")
        classpath ("de.mannodermaus.gradle.plugins:android-junit5:1.5.2.0")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
    }

}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}

subprojects {
    val ktlint by configurations.creating
    dependencies {
        ktlint("com.github.shyiko:ktlint:0.34.2")
    }

    tasks.register<JavaExec>("ktlint") {
        group = "verification"
        description = "Check Kotlin code style."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args("--android", "src/**/*.kt", "src/**/*.kts")
    }

    tasks.register<JavaExec>("ktlintFormat") {
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        main = "com.pinterest.ktlint.Main"
        args("--android", "-F", "src/**/*.kt", "src/**/*.kts")
    }

    afterEvaluate {

        tasks.named("check") {
            dependsOn(ktlint)
        }
        extensions.configure(BaseExtension::class.java) {
            lintOptions {
                xmlReport = true
                isAbortOnError = false
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-progressive")
            }
        }
    }

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