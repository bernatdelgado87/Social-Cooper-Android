package common.media

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream

actual class SharedImage(private val bitmap: android.graphics.Bitmap?) {
    actual fun toByteArray(): ByteArray? {
        return if (bitmap != null) {
            val byteArrayOutputStream = ByteArrayOutputStream()
            @Suppress("MagicNumber") bitmap.compress(
                android.graphics.Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream
            )
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