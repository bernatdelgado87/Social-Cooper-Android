package app.mistercooper.social.di

import comment.viewmodel.CommentViewModel
import publish.viewmodel.PublishViewModel
import feed.viewmodel.HomeViewModel
import registerLogin.viewmodel.RegisterLoginViewModel
import main.viewModel.MainViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { RegisterLoginViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { PublishViewModel(get()) }
    viewModel { CommentViewModel(get(), get()) }
}