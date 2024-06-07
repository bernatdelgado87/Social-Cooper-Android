plugins {
    alias(libs.plugins.kmp.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.kmp.skie)
    alias(libs.plugins.kmpNativeCoroutines)
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
            implementation(project(":domain:domain-common"))
            implementation(project(":shared:ui-shared:ui-comment-shared"))
            implementation(project(":ui:ui-common"))
            implementation(project(":shared:domain-shared:domain-shared-comment"))
            implementation(project(":shared:domain-shared:domain-shared-common"))
            implementation(libs.koin.androidx.compose)
            implementation(libs.androidx.animation.graphics.android)
            api(libs.kmm.viewmodel)
        }
        iosMain.dependencies {
        }
        commonMain.dependencies {
        }

        // Required by KMM-ViewModel
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }
    }
}

android {
    namespace = "app.mistercooper.ui.comment"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}
