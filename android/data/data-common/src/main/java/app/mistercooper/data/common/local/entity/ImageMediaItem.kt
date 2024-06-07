package app.mistercooper.data.common.local.entity

import android.net.Uri
import java.io.File

data class ImageMediaItem(
    val uri: Uri,
    val filename: String,
    val mimeType: String,
    val size: Long,
    val path: String
) {
    val file: File
        get() = File(path)
}