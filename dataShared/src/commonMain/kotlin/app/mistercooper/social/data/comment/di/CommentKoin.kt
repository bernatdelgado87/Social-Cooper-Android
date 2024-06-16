package app.mistercooper.social.data.comment.di

import app.mistercooper.data.comment.repository.CommentRepositoryImpl
import app.mistercooper.data.home.repository.HomeRepositoryImpl
import app.mistercooper.social.data.comment.remote.api.CommentApiImpl
import app.mistercooper.social.data.comment.remote.api.CommentRemoteApi
import app.mistercooper.social.data.home.remote.api.HomeApiImpl
import app.mistercooper.social.data.home.remote.api.HomeRemoteApi
import app.mistercooper.social.data.user.UserRepositoryImpl
import app.mistercooper.social.data.user.remote.api.RegisterLoginApi
import app.mistercooper.social.data.user.remote.api.RegisterLoginApiImpl
import app.mistercooper.social.domain.comment.repository.CommentRepository
import app.mistercooper.social.domain.comment.usecase.GetCommentsUseCase
import app.mistercooper.social.domain.comment.usecase.PublishCommentUseCase
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

val commentModule = module {
    single<CommentRepository> { CommentRepositoryImpl(get()) }
    single<CommentRemoteApi> { CommentApiImpl(get()) }
    single<GetCommentsUseCase> { GetCommentsUseCase(get()) }
    single<PublishCommentUseCase> { PublishCommentUseCase(get()) }
}
