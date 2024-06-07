package app.mistercooper.domain.comment.koin

import app.mistercooper.domain.comment.usecase.GetCommentsUseCase
import app.mistercooper.domain.comment.usecase.PublishCommentUseCase
import org.koin.dsl.module

val commentDomainModule = module {
    single<GetCommentsUseCase> { GetCommentsUseCase(get()) }
    single<PublishCommentUseCase> { PublishCommentUseCase(get()) }
}

