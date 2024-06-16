package app.mistercooper.social.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.compose.composable
import app.mistercooper.ui.registerlogin.LoginOrRegisterScreen
import app.mistercooper.ui.registerlogin.LoginScreen
import app.mistercooper.ui.registerlogin.RegisterScreen
import common.navigation.GlobalNavigator
import common.navigation.NavigationRoute
import feed.HomeScreen
import publish.PublishHomeScreen

fun NavGraphBuilder.getNavGraphBuilder(globalNavigator: GlobalNavigator) = run {
    composable(NavigationRoute.HOME_FEED.name) {
        HomeScreen(globalNavigator = globalNavigator)
    }
    composable(NavigationRoute.LOGIN_OR_REGISTER.name) {
        LoginOrRegisterScreen(
            globalNavigator = globalNavigator
        )
    }
    composable(NavigationRoute.REGISTER.name) {
        RegisterScreen(
            globalNavigator = globalNavigator
        )
    }
    composable(NavigationRoute.PUBLISH_NOW.name) {
        PublishHomeScreen(
            globalNavigator = globalNavigator
        )
    }


    composable(NavigationRoute.LOGIN.name) {
        LoginScreen(
            globalNavigator = globalNavigator
        )
    }
}

