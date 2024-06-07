package app.mistercooper.domain.common.feature.media.koin

import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import app.mistercooper.domain.common.feature.media.usecase.GetImageFromUriUseCase
import app.mistercooper.domain.common.feature.media.usecase.GetMediaImagesFromDeviceUseCase
import org.koin.dsl.module

val mediaDomainModule = module {
    single<GetImageFromUriUseCase> { GetImageFromUriUseCase(get()) }
    single<GetMediaImagesFromDeviceUseCase> { GetMediaImagesFromDeviceUseCase(get()) }
}