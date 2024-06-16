package common.media

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImage {
    fun toByteArray(): ByteArray?
    fun toPairArrayBiteBitmap(): Pair<ByteArray, ImageBitmap>?
}