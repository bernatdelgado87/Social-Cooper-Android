package app.mistercooper.data.publish.repository

import app.mistercooper.data.publish.remote.api.PublishPostRemoteApi
import app.mistercooper.domain.publish.repository.PublishRepository
import app.mistercooper.domain_shared_common.arch.model.GlobalFailure
import java.io.File

class PublishRepositoryImpl(private val publishPostApi: PublishPostRemoteApi) : PublishRepository {
    override suspend fun publishPost(text: String, file: File) {
        try {
            val response = publishPostApi.publish(text, file)
            return
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }
}