package app.mistercooper.social.data.main.di

import app.mistercooper.data.home.repository.MainRepositoryImpl
import app.mistercooper.social.domain.main.repository.MainRepository
import org.koin.dsl.module

val mainDataModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}
