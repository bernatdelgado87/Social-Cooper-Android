package app.mistercooper.data.comment.koin

import app.mistercooper.data.comment.remote.api.CommentApiImpl
import app.mistercooper.data.comment.remote.api.CommentRemoteApi
import app.mistercooper.data.comment.repository.CommentRepositoryImpl
import app.mistercooper.domain.comment.repository.CommentRepository
import org.koin.dsl.module

val commentDataModule = module {
    single<CommentRemoteApi> { CommentApiImpl(get()) }
    single<CommentRepository> { CommentRepositoryImpl(get()) }
}

