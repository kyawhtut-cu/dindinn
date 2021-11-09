plugins {
    library()
    kotlinAndroid()
    kotlinKapt()
    dagger()
    androidGitVersion()
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            debuggable(false)
            jniDebuggable(false)
            renderscriptDebuggable(false)

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

    android.libraryVariants.all {
        val variant = this
        variant.outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val buildOutputPath = "../../release/${androidGitVersion.name()}/"
                output.outputFileName = String.format(
                    "%s%s",
                    buildOutputPath,
                    output.outputFileName
                )
            }
    }
}

dependencies {

    implementation(Libs.kotlinLib)
    implementation(Libs.coreKtx)
    implementation(Libs.appCompact)
    implementation(Libs.material)

    // dependency injection
    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltAndroidCompiler)

    implementation(Libs.retrofit)
    implementation(Libs.okhttp)
    implementation(Libs.retrofitGson)
    implementation(Libs.retrofitRxJava)

    // reactive
    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)
    implementation(Libs.rxKotlin)

    implementation(Libs.glide)
    implementation(Libs.glideOkHttp)
    kapt(Libs.glideCompiler)
}
