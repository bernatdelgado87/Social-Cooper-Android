package app.di

import app.mistercooper.social.data.comment.di.commentModule
import app.mistercooper.social.data.common.di.commonModule
import app.mistercooper.social.data.home.di.homeModule
import app.mistercooper.social.data.main.di.mainDataModule
import app.mistercooper.social.data.publish.di.publishModule
import app.mistercooper.social.data.user.di.userModule
import main.viewModel.MainViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import registerLogin.viewmodel.RegisterLoginViewModel
import comment.viewmodel.CommentViewModel
import publish.viewmodel.PublishViewModel
import feed.viewmodel.HomeViewModel
import main.di.appAndroidModule

fun initKoin() = initKoin() {}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            commonModule,
            mainDataModule,
            userModule,
            homeModule,
            appAndroidModule,
            publishModule,
            commentModule,
            module {
                viewModelOf(::RegisterLoginViewModel)
                viewModelOf(::MainViewModel)
                viewModelOf(::HomeViewModel)
                viewModelOf(::PublishViewModel)
                viewModelOf(::CommentViewModel)
            }
        )


    }