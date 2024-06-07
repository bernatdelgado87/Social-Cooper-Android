package app.mistercooper.data.common.repository

import android.content.Context
import android.net.Uri
import app.mistercooper.data.common.local.LocalMediaDataSource
import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import java.io.File

class MediaRepositoryImpl(context: Context) : MediaRepository {
    private val localMediaDataSource = LocalMediaDataSource(context)

    override suspend fun getSavedImages() {
        localMediaDataSource.fetchSavedImages().map { it.uri }.toList()
    }

    override suspend fun getSavedImageByUri(
        scheme: String
    ): File =
        localMediaDataSource.getImageFromUri(
            Uri.parse(scheme)
        )
}