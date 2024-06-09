plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
}

android {
    namespace = "app.mistercooper.ui.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
    allprojects {
    dependencies {

        implementation(project(":android:domain:domain-common"))
        api(libs.androidx.core.ktx)
        api(libs.androidx.appcompat)
        api(libs.material)
        //remote image
        api(libs.coil)
        implementation(libs.koin.androidx.compose)
        implementation(libs.androidx.navigation.compose)

        //todo check this
        api(libs.androidx.activity.compose)
        //
        api(platform(libs.androidx.compose.bom))
        api(libs.androidx.ui)
        api(libs.androidx.ui.graphics)
        api(libs.androidx.ui.tooling.preview)
        api(libs.androidx.material3)

        //lottie
        implementation(libs.lottie)

        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
    }
}