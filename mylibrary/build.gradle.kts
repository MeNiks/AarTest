import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream
import java.io.FileOutputStream

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(29)
        val currentVersionCode = getCurrentVersionCode()
        versionCode = currentVersionCode
        versionName = getReadableVersionCode(currentVersionCode)
    }
}

renameArtifact()

incrementVersionCode()

fun gerVersionPropertiesName(): String {
    return "version.properties"
}

fun getCurrentVersionCode(): Int {

    //println("Searching In Directory : ${projectDir.absolutePath}")

    val versionPropertiesName = gerVersionPropertiesName()
    val versionPropsFile = file(projectDir.absolutePath + "/" + versionPropertiesName)
    if (!versionPropsFile.exists()) {
        throw  GradleException("Could not read $versionPropertiesName!")
    }

    val versionProps = Properties()
    versionProps.load(FileInputStream(versionPropsFile))
    return versionProps.getProperty("VERSION_CODE", "1").toInt()
}

fun getReadableVersionCode(fileVersionCode: Int): String {
    return if (fileVersionCode <= 10) {
        "1." + (fileVersionCode - 1)
    } else if (fileVersionCode >= 20 && fileVersionCode % 10 == 0) {
        ((fileVersionCode / 10)).toString() + ".9"
    } else {
        (1 + (fileVersionCode / 10)).toString() + "." + ((fileVersionCode % 10) - 1)
    }
}

fun incrementVersionCode() {
    val autoIncrement = project.hasProperty("autoIncrement")
    println("Has autoIncrement : $autoIncrement")
    if (autoIncrement) {

        val versionPropertiesName = gerVersionPropertiesName()
        val versionPropsFile = file(projectDir.absolutePath + "/" + versionPropertiesName)
        if (!versionPropsFile.exists()) {
            throw  GradleException("Could not read $versionPropertiesName!")
        }

        //Incrementing Version code
        val currentVersionCode = getCurrentVersionCode()
        val versionProps = Properties()
        versionProps.load(FileInputStream(versionPropsFile))
        versionProps.setProperty("VERSION_CODE", (currentVersionCode + 1).toString())
        versionProps.store(FileOutputStream(versionPropsFile), null)
    }
}

fun renameArtifact() {
    project.base.archivesBaseName = "${android.defaultConfig.name}-${android.defaultConfig.versionName}"
}
