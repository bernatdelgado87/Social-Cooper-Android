package common.media

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import java.io.InputStream


object BitmapUtils {
    fun getBitmapFromUri(uri: Uri, contentResolver: ContentResolver): Bitmap? {
        var inputStream: InputStream?
        try {
                inputStream = contentResolver.openInputStream(uri)
                val exif = inputStream?.let { ExifInterface(inputStream!!) }
                inputStream?.close()

                inputStream = contentResolver.openInputStream(uri)
                val bmp = BitmapFactory.decodeStream(inputStream)


                val orientation = exif?.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL
                )

                var angle = 0

                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                    angle = 90
                } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                    angle = 180
                } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                    angle = 270
                }

                val mat = Matrix()
                mat.postRotate(angle.toFloat())

                val correctBmp =
                    Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, mat, true)


                inputStream?.close()
                return correctBmp
        } catch (e: Exception) {
            e.printStackTrace()
            println("getBitmapFromUri Exception: ${e.message}")
            println("getBitmapFromUri Exception: ${e.localizedMessage}")
            return null
        }
    }
}