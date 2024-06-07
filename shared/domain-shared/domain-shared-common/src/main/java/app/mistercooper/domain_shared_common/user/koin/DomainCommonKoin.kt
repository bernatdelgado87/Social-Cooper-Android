package app.mistercooper.domain_shared_common.user.koin

import app.mistercooper.domain_shared_common.user.usecase.IsUserRegisteredUseCase
import org.koin.dsl.module

val commonDomainModule = module {
    single<IsUserRegisteredUseCase> { IsUserRegisteredUseCase(get()) }
}

