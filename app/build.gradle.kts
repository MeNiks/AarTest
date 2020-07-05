plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    kotlin("kapt")
    id("com.google.firebase.crashlytics")
    id("de.mannodermaus.android-junit5")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Versions.globalCompileSdkVersion)
    buildToolsVersion(Versions.globalBuildToolsVersion)
    defaultConfig {

        applicationId = "app.niks.aartest"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument(
            "runnerBuilder",
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
        )

        minSdkVersion(Versions.minimumSdkVersion)
        targetSdkVersion(Versions.targetedSdkVersion)

        val currentVersion = Versions.getCurrentVersionCode(projectDir)
        versionCode = currentVersion
        versionName = Versions.getReadableVersionCode(currentVersion)

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
        sourceSets {
            getByName("androidTest") {
                assets.srcDirs(files("$projectDir/schemas"))
            }
        }
    }

    useLibrary("org.apache.http.legacy")

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

        }
    }
    dataBinding.isEnabled = true

    if (project.hasProperty("devBuild")) {
        splits.abi.isEnable = false
        splits.density.isEnable = false
        aaptOptions.cruncherEnabled = false
    }

    dexOptions {
        preDexLibraries = true
    }

    androidExtensions {
        isExperimental = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("debug").java.srcDirs("src/debug/kotlin")
        getByName("release").java.srcDirs("src/release/kotlin")
        getByName("test").java.srcDirs("src/sharedTest/kotlin", "src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/sharedTest/kotlin", "src/androidTest/kotlin")

    }
    packagingOptions {
        exclude("META-INF/LICENSE*")
    }
}

// Rename Artifact
project.base.archivesBaseName = "${android.defaultConfig.name}-${android.defaultConfig.versionName}"

// Auto Increment
if (project.hasProperty("autoIncrement")) {
    Versions.incrementVersionCode(projectDir)
}

dependencies {

    implementation(project(mapOf("path" to ":mylibrary")))
    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoRoutineCoreVersion}")
    implementation("com.squareup:kotlinpoet:${Versions.kotlinPoetVersion}")

    //Support
    implementation("androidx.legacy:legacy-support-v4:${Versions.androidXVersion}")
    implementation("androidx.legacy:legacy-support-v13:${Versions.androidXVersion}")
    implementation("androidx.appcompat:appcompat:${Versions.androidXVersionAppCompact}")
    implementation("androidx.core:core-ktx:${Versions.androidXCoreKtx}")
    implementation("androidx.cardview:cardview:${Versions.androidXVersion}")
    implementation("androidx.recyclerview:recyclerview:${Versions.androidXVersionRecyclerView}")
    implementation("androidx.gridlayout:gridlayout:${Versions.androidXGridLayout}")
    implementation("androidx.transition:transition:${Versions.androidXVersionTransition}")

    //Paging Library
    implementation("androidx.paging:paging-runtime:${Versions.pagingVersion}")
    implementation("androidx.paging:paging-rxjava2:${Versions.pagingVersion}")
    testImplementation("androidx.paging:paging-common:${Versions.pagingVersion}")

    //Constraint Layout
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}")
    implementation("androidx.constraintlayout:constraintlayout-solver:${Versions.constraintLayoutVersion}")

    //Multi Dex
    implementation("androidx.multidex:multidex:${Versions.multidexVersion}")

    //Room
    implementation("androidx.room:room-runtime:${Versions.roomVersion}")
    kapt("androidx.room:room-compiler:${Versions.roomVersion}")
    implementation("androidx.room:room-rxjava2:${Versions.roomVersion}")
    androidTestImplementation("androidx.room:room-testing:${Versions.roomVersion}")
    androidTestImplementation("android.arch.persistence.room:testing:${Versions.roomPersistenceVersion}")

    //Testing
    androidTestImplementation("androidx.annotation:annotation:${Versions.androidXAnnotation}")
    androidTestImplementation("androidx.arch.core:core-testing:${Versions.androidXCoreTesting}")
    androidTestImplementation("androidx.test:runner:${Versions.testRunnerVersion}")
    androidTestImplementation("androidx.test:rules:${Versions.testRules}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.androidXExpressoCoreTesting}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.androidXExtTesting}")
    // 5) Jupiter API & Test Runner, if you don't have it already
    androidTestImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiterVersion}")
    // 6) The instrumentation test companion libraries
    androidTestImplementation("de.mannodermaus.junit5:android-test-core:${Versions.androidTestCoreVersion}")
    androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:${Versions.androidTestCoreVersion}")
    testImplementation("org.json:json:${Versions.orgJsonVersion}")
    testImplementation("junit:junit:${Versions.junitVersion}")

    //Robolectric
    testImplementation("org.robolectric:robolectric:${Versions.robolectricVersion}")
    testImplementation("org.robolectric:shadows-multidex:${Versions.robolectricVersion}")

    //Mockito
    testImplementation("org.mockito:mockito-core:${Versions.mokitoVersion}")
    testImplementation("org.mockito:mockito-inline:${Versions.mokitoVersion}")
    androidTestImplementation("org.mockito:mockito-android:${Versions.mokitoVersion}")

    //Material Design
    implementation("com.google.android.material:material:${Versions.materialDesignVersion}")

    //Play Services
    implementation("com.google.android.gms:play-services-gcm:${Versions.playServiceVersion}")
    implementation("com.google.android.gms:play-services-location:${Versions.playServiceVersion}")

    //Android Navigation
    implementation("android.arch.navigation:navigation-fragment-ktx:${Versions.archNavigationVersion}")
    implementation("android.arch.navigation:navigation-ui-ktx:${Versions.archNavigationVersion}")

    //Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    //Clever Tap
    implementation("com.clevertap.android:clevertap-android-sdk:3.7.2")

    //Glide
    implementation("com.github.bumptech.glide:glide:${Versions.glideVersion}")
    kapt("com.github.bumptech.glide:compiler:${Versions.glideVersion}")
    implementation("jp.wasabeef:glide-transformations:${Versions.glideTransformVersion}")

    //Firebase
    implementation("com.google.firebase:firebase-core:${Versions.firebaseVersion}")
    implementation("com.google.firebase:firebase-analytics:${Versions.firebaseVersion}")
    implementation("com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}")
    implementation("com.google.firebase:firebase-auth:${Versions.firebaseAuthVersion}")
    implementation("com.firebase:firebase-jobdispatcher:${Versions.firebaseJobDispatcher}")
    implementation("com.google.firebase:firebase-crashlytics:${Versions.crashlyticsVersion}")

    //Gson
    implementation("com.google.code.gson:gson:${Versions.gsonVersion}")

    //Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}")

    //Ok Http
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}")
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}")
    testImplementation("com.squareup.okhttp3:mockwebserver:${Versions.okhttpVersion}")

    //Stetho
    implementation("com.facebook.stetho:stetho:${Versions.stethoVersion}")
    implementation("com.facebook.stetho:stetho-okhttp3:${Versions.stethoVersion}")

    //Rx Java
    implementation("io.reactivex.rxjava2:rxjava:${Versions.rxjavaVersion}")

    //Rx Java Android
    implementation("io.reactivex.rxjava2:rxandroid:${Versions.rxandroidVersion}")

    //Rx Java 2 Debug
    implementation("com.akaita.java:rxjava2-debug:${Versions.rxJavaDebug}")

    //Rx Kotlin
    implementation("io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlinVersion}")

    //Rx Relay
    implementation("com.jakewharton.rxrelay2:rxrelay:${Versions.rxrelayVersion}")

    //Rx Binding
    implementation("com.jakewharton.rxbinding2:rxbinding:${Versions.rxBindingVersion}")

    //Rx Shared Prefrence
    implementation("com.f2prateek.rx.preferences2:rx-preferences:${Versions.rxpreferences}")

    //Rx Activity Result
    implementation("com.github.VictorAlbertos:RxActivityResult:${Versions.rxActivityResult}")

    //Rx Permissions
    implementation("com.github.tbruyelle:rxpermissions:${Versions.rxpermissions}")

    //Rx Permissions
    implementation("com.github.tbruyelle:rxpermissions:0.10.2")

    ////Niks Libraries
    ////noinspection GradlePath
    //    implementation(files("releases/release-base-4.6.aar"))
    ////noinspection GradlePath
    //    implementation(files("releases/release-net-1.4.aar"))
    ////noinspection GradlePath
    //    implementation(files("releases/release-timepicker-1.4.aar"))
    ////noinspection GradlePath
    //    implementation(files("releases/release-datepicker-1.9.aar"))
    ////noinspection GradlePath
    //    implementation(files("releases/release-locationhelper-1.2.aar"))

    //Dagger
    implementation("com.google.dagger:dagger:${Versions.daggerVersion}")
    kapt("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
    kapt("com.google.dagger:dagger-android-processor:${Versions.daggerVersion}")
    implementation("com.google.dagger:dagger-android-support:${Versions.daggerVersion}")

    //Anko
    implementation("org.jetbrains.anko:anko-sdk25:${Versions.ankoVersion}")
    implementation("org.jetbrains.anko:anko-appcompat-v7:${Versions.ankoVersion}")
    implementation("org.jetbrains.anko:anko-sdk25-coroutines:${Versions.ankoVersion}")
    implementation("org.jetbrains.anko:anko-appcompat-v7-coroutines:${Versions.ankoVersion}")
    implementation("org.jetbrains.anko:anko-commons:${Versions.ankoVersion}")
    implementation("org.jetbrains.anko:anko-design:${Versions.ankoVersion}")

    //Leak Canary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:${Versions.leakCanaryVersion}")
    releaseImplementation("com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanaryNoOp}")

    //Lifecycle and LiveData and ViewModel
    implementation("android.arch.lifecycle:runtime:${Versions.lifecycleVersion}")
    implementation("android.arch.lifecycle:extensions:${Versions.lifecycleVersion}")
    implementation("android.arch.lifecycle:common-java8:${Versions.lifecycleVersion}")
    kapt("android.arch.lifecycle:compiler:${Versions.lifecycleVersion}")

    //Timber
    implementation("com.jakewharton.timber:timber:${Versions.timberVersion}")

    //ExoPlayer
    implementation("com.google.android.exoplayer:exoplayer:2.9.3")

    //Logging Utitlity
    debugImplementation("com.github.MeNiks:AndroidFileLogger:1.0.23")
}


