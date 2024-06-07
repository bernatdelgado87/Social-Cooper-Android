package app.mistercooper.domain.comment.koin

import app.mistercooper.domain.register_login.usecase.LoginUseCase
import app.mistercooper.domain.register_login.usecase.RegisterUserUseCase
import org.koin.dsl.module

val registerLoginDomainModule = module {
    single<LoginUseCase> { LoginUseCase(get()) }
    single<RegisterUserUseCase> { RegisterUserUseCase(get()) }
}

