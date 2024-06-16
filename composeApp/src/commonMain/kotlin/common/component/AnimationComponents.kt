package app.mistercooper.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxSize())
        //.background(color = Color.White.copy(alpha = 0.1f))) {
       /* val preloaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.anim_loading
            )
        )

        val preloaderProgress by animateLottieCompositionAsState(
            preloaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true
        )


        LottieAnimation(
            modifier = Modifier.align(Alignment.Center),
            composition = preloaderLottieComposition,
            progress = { preloaderProgress },
        )*/
    }
