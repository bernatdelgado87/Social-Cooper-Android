package app.mistercooper.social.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import registerLogin.view.LoginOrRegisterScreen
import registerLogin.view.LoginScreen
import registerLogin.view.RegisterScreen
import comment.CommentsScreen
import common.navigation.NavigationRoute
import feed.HomeScreen
import publish.PublishHomeScreen

fun NavGraphBuilder.getNavGraphBuilder(globalNavigator: NavController) = run {
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
    composable(NavigationRoute.COMMENTS.name + "/{postId}", arguments = listOf(navArgument("postId") {type = NavType.LongType})) {
        val postId = requireNotNull(it.arguments?.getLong("postId"))
        CommentsScreen(
            postId,
            globalNavigator
        )
    }

}

fun NavController.restartNavigation() {
    navigate(NavigationRoute.HOME_FEED.name) {
        popUpToRoute
    }
}

