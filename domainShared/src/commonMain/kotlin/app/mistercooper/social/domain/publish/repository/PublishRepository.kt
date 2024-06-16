package app.mistercooper.social.domain.publish.repository

interface PublishRepository {
    suspend fun publishPost(text: String, byteArray: ByteArray, username: String)
}