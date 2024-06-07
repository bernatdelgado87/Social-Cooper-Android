package app.mistercooper.data.register_login.koin

import app.mistercooper.data.register_login.remote.api.RegisterLoginApi
import app.mistercooper.data.register_login.remote.api.RegisterLoginApiImpl
import app.mistercooper.data.register_login.repository.UserRepositoryImpl
import app.mistercooper.domain.register_login.repository.UserRepository
import org.koin.dsl.module

val registerLoginDataModule = module {
    single<RegisterLoginApi> { RegisterLoginApiImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}
