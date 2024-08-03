package common.media

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream

actual class SharedImage(private val bitmap: android.graphics.Bitmap?) {
    actual fun toByteArray(): ByteArray? {
        return if (bitmap != null) {
            val byteArrayOutputStream = ByteArrayOutputStream()
            Log.i("Camera", "pre compression")
            @Suppress("MagicNumber") bitmap.compress(
                android.graphics.Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream
            )
            Log.i("Camera", "post compression")
            byteArrayOutputStream.toByteArray()
        } else {
            println("toByteArray null")
            null
        }
    }

    actual fun toPairArrayBiteBitmap(): Pair<ByteArray, ImageBitmap>? {
        val byteArray = toByteArray()
        return if (byteArray != null) {
            return Pair(byteArray, BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap())
        } else {
            println("toImageBitmap null")
            null
        }
    }
}