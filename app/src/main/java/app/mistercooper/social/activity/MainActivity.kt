package app.mistercooper.social.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.mistercooper.ui.common.navigation.GlobalNavigator
import app.mistercooper.social.navigation.getNavGraphBuilder
import app.mistercooper.social.theme.SocialCooperAndroidTheme
import app.mistercooper.ui.common.components.LoadingComponent
import app.mistercooper.ui.common.navigation.CustomNavigator
import app.mistercooper.ui.common.navigation.NavigationRoute
import app.mistercooper.ui_main_shared.MainViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    val customNavigator: CustomNavigator = get()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialCooperAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nativeNavController = rememberNavController()

                    val viewModel: MainViewModel = koinViewModel()
                    val state = viewModel.isRgisteredState.collectAsState()

                    state.value?.let { isRegistered ->
                        NavHost(
                            navController = nativeNavController,
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
    }
}