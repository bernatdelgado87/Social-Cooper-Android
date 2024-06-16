package app.mistercooper.social.data.home.di

import app.mistercooper.data.home.repository.HomeRepositoryImpl
import app.mistercooper.social.data.home.remote.api.HomeApiImpl
import app.mistercooper.social.data.home.remote.api.HomeRemoteApi
import app.mistercooper.social.data.user.UserRepositoryImpl
import app.mistercooper.social.data.user.remote.api.RegisterLoginApi
import app.mistercooper.social.data.user.remote.api.RegisterLoginApiImpl
import app.mistercooper.social.domain.home.repository.HomeRepository
import app.mistercooper.social.domain.home.usecase.GetFeedUseCase
import app.mistercooper.social.domain.home.usecase.PublishLikeUseCase
import app.mistercooper.social.domain.main.usecase.IsUserRegisteredUseCase
import app.mistercooper.social.domain.registerLogin.repository.UserRepository
import app.mistercooper.social.domain.registerLogin.usecase.LoginUseCase
import app.mistercooper.social.domain.registerLogin.usecase.RegisterUserUseCase
import com.jetbrains.kmpapp.local.KeyValueStorageImpl
import com.jetbrains.kmpapp.local.KeyValueStorageInterface
import org.koin.dsl.module

val homeModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<HomeRemoteApi> { HomeApiImpl(get()) }
    single<GetFeedUseCase> { GetFeedUseCase(get()) }
    single<PublishLikeUseCase> { PublishLikeUseCase(get()) }
}
