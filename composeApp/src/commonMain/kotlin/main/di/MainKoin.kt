package main.di

import app.navigation.CustomNavigatorImpl
import common.navigation.CustomNavigator
import org.koin.dsl.module

val appAndroidModule = module {
    single<CustomNavigator> { CustomNavigatorImpl() }
}
