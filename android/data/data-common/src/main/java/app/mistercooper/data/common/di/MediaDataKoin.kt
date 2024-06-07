package app.mistercooper.data.common.di

import app.mistercooper.data.common.repository.MediaRepositoryImpl
import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mediaDataModule = module {
    single<MediaRepository> { MediaRepositoryImpl(androidContext()) }
}