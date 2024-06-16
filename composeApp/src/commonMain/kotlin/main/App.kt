package main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import app.mistercooper.social.navigation.getNavGraphBuilder
import app.mistercooper.ui.common.components.LoadingComponent
import coil3.compose.setSingletonImageLoaderFactory
import common.navigation.CustomNavigator
import common.navigation.GlobalNavigator
import common.navigation.NavigationRoute
import main.viewModel.MainViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        setImageLoader()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val nativeNavController = rememberNavController()
            val customNavigator: CustomNavigator = koinInject()

            val viewModel: MainViewModel = koinViewModel<MainViewModel>()
            val state = viewModel.isRgisteredState.collectAsState()

            state.value?.let { isRegistered ->
                NavHost(navController = nativeNavController,
                    startDestination = if (isRegistered) {
                        NavigationRoute.HOME_FEED.name
                    } else {
                        NavigationRoute.LOGIN_OR_REGISTER.name
                    }
                ) {
                    getNavGraphBuilder(
                        GlobalNavigator(
                            nativeController = nativeNavController,
                            customNavigator = customNavigator
                        )
                    )
                }
            } ?: LoadingComponent()
        }
    }
}
