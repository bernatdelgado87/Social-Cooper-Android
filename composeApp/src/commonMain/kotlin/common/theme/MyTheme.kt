package common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.compose.backgroundDark
import com.example.compose.backgroundLight
import com.example.compose.errorDark
import com.example.compose.errorLight
import com.example.compose.onBackgroundDark
import com.example.compose.onBackgroundLight
import com.example.compose.onErrorDark
import com.example.compose.onErrorLight
import com.example.compose.onPrimaryDark
import com.example.compose.onPrimaryLight
import com.example.compose.onSecondaryDark
import com.example.compose.onSecondaryLight
import com.example.compose.onSurfaceDark
import com.example.compose.onSurfaceLight
import com.example.compose.primaryDark
import com.example.compose.primaryLight
import com.example.compose.secondaryDark
import com.example.compose.secondaryLight
import com.example.compose.surfaceDark
import com.example.compose.surfaceLight

private val lightScheme = lightColors(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    error = errorLight,
    onError = onErrorLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
)

private val darkScheme = darkColors(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    error = errorDark,
    onError = onErrorDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
)

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkScheme
    } else {
        lightScheme
    }

    MaterialTheme(
        colors = colors,
        typography = customTypography(),
        shapes = Shapes(),
        content = content
    )
}

@Composable
fun customTypography(): Typography {
    val muliRegular = FontFamily(
        font(
            "Muli", "muli", FontWeight.Normal, FontStyle.Normal
        )
    )
    val poppinsBold = FontFamily(
        font(
            "poppins_bold", "poppins_bold", FontWeight.Normal, FontStyle.Normal
        )
    )

    return Typography(
        h1 = TextStyle(fontFamily = poppinsBold, fontSize = 32.sp),
        h2 = TextStyle(fontFamily = poppinsBold, fontSize = 26.sp),
        h3 = TextStyle(fontFamily = poppinsBold, fontSize = 22.sp),
        body1 = TextStyle(fontFamily = muliRegular)
    )
}

@Composable
expect fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font
