package main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.mistercooper.social.domain.registerLogin.model.RegisterUserModel
import app.mistercooper.social.navigation.getNavGraphBuilder
import app.mistercooper.ui.common.components.LoadingComponent
import common.navigation.NavigationRoute
import common.theme.MySharedTheme
import main.viewModel.MainViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun SocialApp(apiKey: String) {
    MySharedTheme {
        setImageLoader()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val nativeNavController = rememberNavController()

            val viewModel: MainViewModel = koinViewModel<MainViewModel>()
            val state = viewModel.isRgisteredState.collectAsState()

            state.value?.let { isRegistered ->
                if (isRegistered) {
                    NavHost(
                        navController = nativeNavController,
                        startDestination = NavigationRoute.HOME_FEED.name
                    ) {
                        getNavGraphBuilder(
                            nativeNavController
                        )
                    }
                } else {
                    viewModel.register(RegisterUserModel(apikey = apiKey))
                }

            } ?: LoadingComponent()
        }
    }
}
