package app.mistercooper.social.data.main.di

import app.mistercooper.social.domain.main.usecase.IsUserRegisteredUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val IsUserRegisteredUseCase : IsUserRegisteredUseCase by inject()
}