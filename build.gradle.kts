// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val kotlin_version by extra("1.4.32")
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Libs.gradleAndroid)
        classpath(Libs.kotlin)
        classpath(Libs.dagger)
        classpath(Libs.navigationSafeArgs)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts.kts.kts.kts.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
