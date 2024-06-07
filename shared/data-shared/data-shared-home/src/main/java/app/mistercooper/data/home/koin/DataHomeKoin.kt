package app.mistercooper.data.home.koin

import app.mistercooper.data.home.remote.api.HomeApiImpl
import app.mistercooper.data.home.remote.api.HomeRemoteApi
import app.mistercooper.data.home.repository.HomeRepositoryImpl
import app.mistercooper.domain.home.repository.HomeRepository
import org.koin.dsl.module

val homeDataModule = module {
    single<HomeRemoteApi> { HomeApiImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}

