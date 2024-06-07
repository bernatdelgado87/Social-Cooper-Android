package app.mistercooper.domain.comment.koin

import app.mistercooper.social.domain.feature.publish.usecase.PublishPostUseCase
import org.koin.dsl.module

val publishDomainModule = module {
    single<PublishPostUseCase> { PublishPostUseCase(get()) }
}

