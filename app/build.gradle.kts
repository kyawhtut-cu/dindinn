import java.util.*

plugins {
    androidApp()
    kotlinAndroid()
    kotlinKapt()
    dagger()
    navigationSafeArgsKtx()
    androidGitVersion()
}

val configProperties = Properties()
configProperties.load(file("${rootDir}/config.properties").inputStream())
val BASE_URL: String = configProperties.getProperty("BASE_URL", "")

val appName = hashMapOf(
    "debug" to "app-debug.apk",
    "release" to "app-release.apk"
)

var versionCode = androidGitVersion.code()
if (versionCode == 0) {
    versionCode = 1
}

android {

    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {

        applicationId = "com.kyawhut.codetest.order"

        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)

        versionCode = versionCode
        versionName = androidGitVersion.name()

        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigString("BASE_URL", BASE_URL)

    }

    buildTypes {

        getByName("release") {
            debuggable(false)
            jniDebuggable(false)
            renderscriptDebuggable(false)

            minifyEnabled(true)
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    android.applicationVariants.all {
        val variant = this
        variant.outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val buildOutputPath = "../../release/${variant.versionName}/%s"
                output.outputFileName = String.format(
                    buildOutputPath,
                    appName[variant.buildType.name]
                )
            }
    }
}

dependencies {

    testImplementation(Libs.junit)
    androidTestImplementation(Libs.testJunit)
    androidTestImplementation(Libs.espresso)
    testImplementation(Libs.mockito)
    testImplementation(Libs.coreTesting)

    implementation(Libs.kotlinLib)
    implementation(Libs.coreKtx)
    implementation(Libs.appCompact)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUI)
    implementation(Libs.vectorDrawable)
    implementation(Libs.swipeRefresh)
    implementation(Libs.liveDataKtx)

    // retrofit
    implementation(Libs.retrofit)
    implementation(Libs.loggingInterceptor)

    implementation(Libs.gson)

    // dependency injection
    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)

    // ViewModel and LiveData
    implementation(Libs.lifeCycleExt)
    implementation(Libs.fragmentKtx)

    //Timber(Logging)
    implementation(Libs.timber)

    // reactive
    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)
    implementation(Libs.rxKotlin)

    implementation(project(":share"))
}
