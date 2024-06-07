package app.mistercooper.domain.home.koin

import app.mistercooper.domain.home.usecase.GetFeedUseCase
import app.mistercooper.domain.home.usecase.PublishLikeUseCase
import org.koin.dsl.module

val homeDomainModule = module {
    single<GetFeedUseCase> { GetFeedUseCase(get()) }
    single<PublishLikeUseCase> { PublishLikeUseCase(get()) }
}

