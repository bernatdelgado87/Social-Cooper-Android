package app.mistercooper.domain.publish.repository

import java.io.File

interface PublishRepository {
    suspend fun publishPost(text: String, file: File)
}