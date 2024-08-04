package app.mistercooper.data.home.repository

import app.mistercooper.domain_shared_common.arch.model.GlobalFailure
import app.mistercooper.social.data.home.remote.api.HomeRemoteApi
import app.mistercooper.social.data.home.remote.dto.mapper.toModel
import app.mistercooper.social.domain.home.model.FeedModel
import app.mistercooper.social.domain.home.repository.HomeRepository

class HomeRepositoryImpl(private val homeRemoteApi: HomeRemoteApi) : HomeRepository {
    val n = 2
    var offset = 0

    override suspend fun getFeed(): FeedModel {
        try {
            val response =  homeRemoteApi.getSocialFeed(
                n = n,
                offset = offset
            ).toModel()
            offset=+n
            return response
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