package app.mistercooper.data.common.local

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import app.mistercooper.data.common.local.entity.ImageMediaItem
import kotlinx.coroutines.flow.flow
import java.io.File

class LocalMediaDataSource(private val context: Context) {

    fun fetchSavedImages() = flow {
        val externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.DATA,
        )

        val cursor = context.contentResolver.query(
            externalContentUri,
            projection,
            null,
            null,
            "${MediaStore.Files.FileColumns.DATE_ADDED} DESC"
        ) ?: throw Exception("Query could not be executed")

        cursor.use {
            while (cursor.moveToNext()) {
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
                val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
                val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
                val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)

                val contentUri = ContentUris.withAppendedId(
                    externalContentUri,
                    cursor.getLong(idColumn)
                )
                emit(
                    ImageMediaItem(
                        uri = contentUri,
                        filename = cursor.getString(displayNameColumn),
                        size = cursor.getLong(sizeColumn),
                        mimeType = cursor.getString(mimeTypeColumn),
                        path = cursor.getString(dataColumn),
                    )
                )
            }
        }
    }

    fun getImageFromUri(uri: Uri): File {
        try {
            context.contentResolver.openInputStream(uri)?.use { input ->
                val file = generateFile()
                file.outputStream().use { output ->
                    input.copyTo(output)
                    return file
                }
            }
        } catch (e: Exception){
            e.printStackTrace()
            throw Exception("Error creating file!")
        }
        throw Exception("Error creating file!")
    }


    private fun generateFileName() = "${System.currentTimeMillis()}.jpg"

    private fun generateFile(): File {
        val imageAppFolder = File(context.cacheDir, "photos").also { it.mkdir() }
        return File(imageAppFolder, generateFileName())
    }

}