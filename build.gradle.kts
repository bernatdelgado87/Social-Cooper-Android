// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
    }
}

plugins {
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.kspAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
    alias(libs.plugins.kotlinxSerialization) apply false
    alias(libs.plugins.kmpNativeCoroutines) apply false

    alias(libs.plugins.kmp.kotlinMultiplatform) apply false
    alias(libs.plugins.kmp.skie) apply false

}