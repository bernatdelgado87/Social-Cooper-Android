package app.mistercooper.domain.home.usecase

import app.mistercooper.domain.home.model.FeedModel
import app.mistercooper.domain.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFeedUseCase (private val socialRepository: HomeRepository) {
    operator fun invoke(): Flow<FeedModel> {
        return flow { emit(socialRepository.getFeed()) }
    }
}