plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
}

android {

    compileSdkVersion(Versions.globalCompileSdkVersion)
    buildToolsVersion(Versions.globalBuildToolsVersion)

    defaultConfig {

        minSdkVersion(Versions.minimumSdkVersion)
        targetSdkVersion(Versions.targetedSdkVersion)

        val currentVersion = Versions.getCurrentVersionCode(projectDir)
        versionCode = currentVersion
        versionName = Versions.getReadableVersionCode(currentVersion)
    }
}

// Rename Artifact
project.base.archivesBaseName = "${android.defaultConfig.name}-${android.defaultConfig.versionName}"

// Auto Increment
if (project.hasProperty("autoIncrement")) {
    Versions.incrementVersionCode(projectDir)
}
