package common.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import common.theme.AbstractResourceImpl
import androidx.compose.ui.text.platform.Font
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.MissingResourceException
import org.jetbrains.compose.resources.Resource

private val cache: MutableMap<String, Font> = mutableMapOf()
@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font {
    return cache.getOrPut(res) {
        val byteArray = runBlocking {
            resource("font/$res.ttf").readBytes()
        }
        androidx.compose.ui.text.platform.Font(res, byteArray, weight, style)
    }
}