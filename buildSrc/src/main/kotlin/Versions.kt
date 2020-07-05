import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties

object Versions {
    const val kotlinVersion = "1.3.71"
    const val kotlinCoRoutineCoreVersion = "1.3.5"
    const val kotlinPoetVersion = "1.5.0"

    //Kotlin
    const val globalCompileSdkVersion = 28
    const val globalBuildToolsVersion = "28.0.3"

    const val minimumSdkVersion = 19
    const val targetedSdkVersion = 29

    const val crashlyticsVersion = "17.0.0-beta03"

    //Support
    const val androidXVersion = "1.0.0"
    const val androidXAnnotation = "1.1.0"
    const val androidXVersionAppCompact = "1.1.0"
    const val androidXCoreKtx = "1.2.0"
    const val androidXVersionRecyclerView = "1.1.0"
    const val androidXGridLayout = "1.0.0"
    const val androidXVersionTransition = "1.3.1"

    //Material Design
    const val materialDesignVersion = "1.2.0-alpha05"

    //Play Services
    const val playServiceVersion = "17.0.0"

    //Paging Library
    const val pagingVersion = "2.1.2"

    //Constraint Layout
    const val constraintLayoutVersion = "2.0.0-beta4"

    //Multidex
    const val multidexVersion = "2.0.1"

    //Android Navigation
    const val archNavigationVersion = "1.0.0"

    //Glide
    const val glideVersion = "4.11.0"
    const val glideTransformVersion = "4.0.0"

    //Firebase
    const val firebaseVersion = "17.3.0"
    const val firebaseMessaging = "20.1.5"
    const val firebaseAuthVersion = "19.3.0"
    const val firebaseJobDispatcher = "0.8.5"
    const val firebaseCrashlytics = "17.0.0-beta03"

    //Retrofit & OkHttp
    const val retrofitVersion = "2.8.1"

    //Ok Http
    const val okhttpVersion = "4.4.1"

    //Stetho
    const val stethoVersion = "1.5.1"

    //Rx Java
    const val rxjavaVersion = "2.2.19"

    //Rx Java Android
    const val rxandroidVersion = "2.1.1"

    //Rx Java 2 Debug
    const val rxJavaDebug = "1.4.0"

    //Rx Kotlin
    const val rxkotlinVersion = "2.4.0"

    //Rx Relay
    const val rxrelayVersion = "2.1.1"

    //Rx Binding
    const val rxBindingVersion = "2.2.0"

    //Rx Shared Preference
    const val rxpreferences = "2.0.0"

    //Rx Sharedpreference
    const val rxActivityResult = "0.5.0-2.x"

    //Rx Permissions
    const val rxpermissions = "0.10.2"

    //Roo
    const val roomVersion = "2.2.5"
    const val roomPersistenceVersion = "1.1.1"

    //Dagger
    const val daggerVersion = "2.27"

    //Anko
    const val ankoVersion = "0.10.4"

    //Leak Canary
    const val leakCanaryVersion = "2.2"
    const val leakCanaryNoOp = "1.6.3"

    //Lifecycle and LiveData and ViewModel
    const val lifecycleVersion = "1.1.1"

    //Timber
    const val timberVersion = "4.7.1"

    //Gson
    const val gsonVersion = "2.8.6"

    //Testing
    const val androidXCoreTesting = "2.1.0"
    const val androidXExpressoCoreTesting = "3.1.0-beta02"
    const val androidXExtTesting = "1.1.1"
    const val junitVersion = "4.13"
    const val junitJupiterVersion = "5.6.1"
    const val testRunnerVersion = "1.3.0-alpha05"
    const val testRules = "1.3.0-alpha05"
    const val androidTestCoreVersion = "1.1.0"

    //Robo Electric
    const val robolectricVersion = "4.3.1"

    //Mockito
    const val mokitoVersion = "3.3.3"

    //Org Json
    const val orgJsonVersion = "20190722"

    fun gerVersionPropertiesName(): String {
        return "version.properties"
    }

    fun getCurrentVersionCode(projectDir: File): Int {

        val versionPropertiesName = gerVersionPropertiesName()

        println("Searching $versionPropertiesName In Directory : ${projectDir.absolutePath}")

        val versionPropsFile = File(projectDir.absolutePath + "/" + versionPropertiesName)//file(projectDir.absolutePath + "/" + versionPropertiesName)
        if (!versionPropsFile.exists()) {
            throw  IllegalStateException("Could not read $versionPropertiesName at ${projectDir.absolutePath}")
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

    fun incrementVersionCode(projectDir: File) {
        val versionPropertiesName = gerVersionPropertiesName()
        val versionPropsFile = File(projectDir.absolutePath + "/" + versionPropertiesName)//file(projectDir.absolutePath + "/" + versionPropertiesName)
        if (!versionPropsFile.exists()) {
            throw  IllegalStateException("Could not read $versionPropertiesName!")
        }

        //Incrementing Version code
        val currentVersionCode = getCurrentVersionCode(projectDir)
        val versionProps = Properties()
        versionProps.load(FileInputStream(versionPropsFile))
        versionProps.setProperty("VERSION_CODE", (currentVersionCode + 1).toString())
        versionProps.store(FileOutputStream(versionPropsFile), null)
    }
}
