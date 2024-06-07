package app.mistercooper.domain.home.repository

interface HomeRepository {
    suspend fun getFeed(): app.mistercooper.domain.home.model.FeedModel
    suspend fun publishLike(postId: Long, like: Boolean)
}