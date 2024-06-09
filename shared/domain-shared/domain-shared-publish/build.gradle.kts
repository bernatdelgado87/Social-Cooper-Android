plugins {
    alias(libs.plugins.kmp.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.kspAndroid)
    alias(libs.plugins.kmpNativeCoroutines)
    alias(libs.plugins.kmp.skie)

}

kotlin {
    task("testClasses")
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
        }
        iosMain.dependencies {
        }
        commonMain.dependencies {
            implementation(project(":shared:domain-shared:domain-shared-common"))
            implementation(libs.koin.core)
        }

        // Required by KMM-ViewModel
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
    }
}


android {
    namespace = "app.mistercooper.domain.publish"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}
