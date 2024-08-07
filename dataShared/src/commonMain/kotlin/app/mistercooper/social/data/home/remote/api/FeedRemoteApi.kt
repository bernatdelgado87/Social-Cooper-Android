package app.mistercooper.social.data.home.remote.api

import app.mistercooper.social.data.home.remote.dto.request.PublishLikeBodyDTO
import app.mistercooper.social.data.home.remote.dto.response.LikeResponseDTO
import app.mistercooper.social.data.home.remote.dto.response.MultimediaFeedDTO
import com.jetbrains.kmpapp.remote.api.CommonApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

interface HomeRemoteApi {
    suspend fun getSocialFeed(n: Int, offset: Int): MultimediaFeedDTO
    suspend fun publishLike(postId: Long, like: Boolean): LikeResponseDTO
}
class HomeApiImpl(private val client: HttpClient) : HomeRemoteApi {
    override suspend fun getSocialFeed(n: Int, offset: Int): MultimediaFeedDTO {
        return try {
            client.get(CommonApi.SOCIAL_API_URL + "feed"){
                url {
                    parameters.append("n", n.toString())
                    parameters.append("offset", offset.toString())
                }
            }.call.response.body<MultimediaFeedDTO>()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun publishLike(postId: Long, like: Boolean): LikeResponseDTO {
        return try {
            client.post(CommonApi.SOCIAL_API_URL + "like") {
               setBody(PublishLikeBodyDTO(postId, like))
            }.call.response.body<LikeResponseDTO>()
        } catch (e: Exception) {
            throw e
        }
    }
}