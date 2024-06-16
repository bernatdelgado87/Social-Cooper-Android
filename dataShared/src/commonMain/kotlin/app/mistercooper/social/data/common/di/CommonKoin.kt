package app.mistercooper.social.data.common.di

import app.mistercooper.social.domain.main.usecase.IsUserRegisteredUseCase
import app.mistercooper.social.domain.registerLogin.repository.UserRepository
import app.mistercooper.social.domain.registerLogin.usecase.LoginUseCase
import app.mistercooper.social.domain.registerLogin.usecase.RegisterUserUseCase
import com.jetbrains.kmpapp.local.KeyValueStorageImpl
import com.jetbrains.kmpapp.local.KeyValueStorageInterface
import com.jetbrains.kmpapp.remote.api.createKtorClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val commonModule = module {
    //main
    single<IsUserRegisteredUseCase> { IsUserRegisteredUseCase(get()) }
    single<KeyValueStorageInterface> { KeyValueStorageImpl() }
    single<HttpClient> { createKtorClient(get()) }

}
