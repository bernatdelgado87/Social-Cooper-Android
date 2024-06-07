package app.mistercooper.data.publish.koin

import app.mistercooper.data.publish.remote.api.PublishPostRemoteApi
import app.mistercooper.data.publish.remote.api.PublishPostRemoteApiImpl
import app.mistercooper.data.publish.repository.PublishRepositoryImpl
import app.mistercooper.domain.publish.repository.PublishRepository
import org.koin.dsl.module


val publishDataModule = module {
    single<PublishPostRemoteApi> { PublishPostRemoteApiImpl(get()) }
    single<PublishRepository> { PublishRepositoryImpl(get()) }
}
