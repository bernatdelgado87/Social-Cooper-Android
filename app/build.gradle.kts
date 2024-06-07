
plugins {
    alias(libs.plugins.kmp.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    kotlin("plugin.serialization")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(project(":android:data:data-common"))
            implementation(project(":android:domain:domain-common"))
            implementation(project(":android:ui:ui-common"))
            implementation(project(":android:ui:ui-home"))
            implementation(project(":android:ui:ui-publish"))
            implementation(project(":android:ui:ui-registerLogin"))
            implementation(project(":android:ui:ui-comment"))
            implementation(project.dependencies.platform(libs.androidx.compose.bom))
            implementation(libs.androidx.navigation.compose)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.viewmodel.compose)
            implementation(libs.koin.androidx.compose)
            implementation(libs.coil.compose)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.runtime.ktx)
        }
        commonMain.dependencies {
            implementation(project(":shared:data-shared:data-shared-comment"))
            implementation(project(":shared:data-shared:data-shared-common"))
            implementation(project(":shared:data-shared:data-shared-home"))
            implementation(project(":shared:data-shared:data-shared-main"))
            implementation(project(":shared:data-shared:data-shared-publish"))
            implementation(project(":shared:data-shared:data-shared-register-login"))
            implementation(project(":shared:domain-shared:domain-shared-comment"))
            implementation(project(":shared:domain-shared:domain-shared-common"))
            implementation(project(":shared:domain-shared:domain-shared-home"))
            implementation(project(":shared:domain-shared:domain-shared-publish"))
            implementation(project(":shared:domain-shared:domain-shared-register-login"))
            implementation(project(":shared:ui-shared:ui-comment-shared"))
            implementation(project(":shared:ui-shared:ui-home-shared"))
            implementation(project(":shared:ui-shared:ui-main-shared"))
            implementation(project(":shared:ui-shared:ui-publish-shared"))
            implementation(project(":shared:ui-shared:ui-registerlogin-shared"))
        }
    }
}


android {
    namespace = "app.mistercooper.social"
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/main/res")
    sourceSets["main"].resources.srcDirs("src/main/resources")

    defaultConfig {
        applicationId = "app.mistercooper.social"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}