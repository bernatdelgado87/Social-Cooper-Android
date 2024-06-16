package app.mistercooper.social.data.publish.di

import app.mistercooper.data.publish.remote.api.PublishPostRemoteApi
import app.mistercooper.data.publish.remote.api.PublishPostRemoteApiImpl
import app.mistercooper.data.publish.repository.PublishRepositoryImpl
import app.mistercooper.social.domain.publish.repository.PublishRepository
import app.mistercooper.social.domain.publish.usecase.PublishPostUseCase
import org.koin.dsl.module

val publishModule = module {
    single<PublishRepository> { PublishRepositoryImpl(get()) }
    single<PublishPostRemoteApi> { PublishPostRemoteApiImpl(get()) }
    single<PublishPostUseCase> { PublishPostUseCase(get()) }
}
