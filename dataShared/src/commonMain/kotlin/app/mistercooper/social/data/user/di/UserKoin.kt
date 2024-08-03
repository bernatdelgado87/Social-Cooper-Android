package app.mistercooper.social.data.user.di

import app.mistercooper.social.data.user.UserRepositoryImpl
import app.mistercooper.social.data.user.remote.api.RegisterLoginApi
import app.mistercooper.social.data.user.remote.api.RegisterLoginApiImpl
import app.mistercooper.social.domain.registerLogin.repository.UserRepository
import app.mistercooper.social.domain.registerLogin.usecase.LoginUseCase
import app.mistercooper.social.domain.registerLogin.usecase.UpdateExistingUserUseCase
import app.mistercooper.social.domain.registerLogin.usecase.RegisterUserUseCase
import org.koin.dsl.module

val userModule = module {
    //register
    single<RegisterUserUseCase> { RegisterUserUseCase(get()) }
    single<UpdateExistingUserUseCase> { UpdateExistingUserUseCase(get()) }
    single<LoginUseCase> { LoginUseCase(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<RegisterLoginApi> { RegisterLoginApiImpl(get()) }

}
