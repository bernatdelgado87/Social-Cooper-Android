package app.mistercooper.data.main.koin

import app.mistercooper.data.home.repository.MainRepositoryImpl
import app.mistercooper.domain_shared_common.user.repository.MainRepository
import org.koin.dsl.module

val mainDataModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
}
