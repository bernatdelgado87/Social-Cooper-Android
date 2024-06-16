package app.mistercooper.social.domain.home.repository

import app.mistercooper.social.domain.home.model.FeedModel

interface HomeRepository {
    suspend fun getFeed(): FeedModel
    suspend fun publishLike(postId: Long, like: Boolean)
}