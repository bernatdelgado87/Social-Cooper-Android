package app.mistercooper.social.di

import app.mistercooper.data.common.repository.MediaRepositoryImpl
import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import app.mistercooper.social.application.utils.ApplicationBuildConfigFieldsProvider
import app.mistercooper.social.navigation.CustomNavigatorImpl
import app.mistercooper.ui.common.navigation.CustomNavigator
import app.mistercooper.ui.common.utils.BuildConfigFieldsProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appAndroidModule = module {
    single<ApplicationBuildConfigFieldsProvider> { ApplicationBuildConfigFieldsProvider() }
    single<BuildConfigFieldsProvider> { ApplicationBuildConfigFieldsProvider() }
    single<CustomNavigator> { CustomNavigatorImpl() }
}
