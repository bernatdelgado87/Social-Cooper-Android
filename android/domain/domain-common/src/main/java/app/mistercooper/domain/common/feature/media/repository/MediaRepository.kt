package app.mistercooper.domain.common.feature.media.repository

import java.io.File

interface MediaRepository {
    suspend fun getSavedImages(): Unit

    suspend fun getSavedImageByUri(
        scheme: String
    ): File
}