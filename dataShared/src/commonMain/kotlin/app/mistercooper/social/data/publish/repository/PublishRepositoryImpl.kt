package app.mistercooper.data.publish.repository

import app.mistercooper.data.publish.remote.api.PublishPostRemoteApi
import app.mistercooper.domain_shared_common.arch.model.GlobalFailure
import app.mistercooper.social.domain.publish.repository.PublishRepository

class PublishRepositoryImpl(private val publishPostApi: PublishPostRemoteApi) : PublishRepository {
    override suspend fun publishPost(text: String, file: ByteArray, username: String) {
        try {
            val response = publishPostApi.publish(text, file, username)
            return
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }
}