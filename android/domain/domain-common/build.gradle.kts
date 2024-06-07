plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //coroutines
    api(libs.kotlinx.coroutines)
    //todo android for URI
    //implementation(libs.android.library)
    implementation(libs.koin.core)

}