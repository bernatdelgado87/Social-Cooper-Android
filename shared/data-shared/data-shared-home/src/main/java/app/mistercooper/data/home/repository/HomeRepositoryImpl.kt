package app.mistercooper.data.home.repository

import app.mistercooper.data.home.remote.api.HomeRemoteApi
import app.mistercooper.data.home.remote.dto.mapper.toModel
import app.mistercooper.domain.home.model.FeedModel
import app.mistercooper.domain.home.repository.HomeRepository
import app.mistercooper.domain_shared_common.arch.model.GlobalFailure

class HomeRepositoryImpl(private val homeRemoteApi: HomeRemoteApi) : HomeRepository {
    override suspend fun getFeed(): FeedModel {
        try {
            val response = homeRemoteApi.getSocialFeed()
            return response.toModel()
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }

    override suspend fun publishLike(postId: Long, like: Boolean) {
        try {
            homeRemoteApi.publishLike(
                postId = postId,
                like = like
            )
            return
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }
}