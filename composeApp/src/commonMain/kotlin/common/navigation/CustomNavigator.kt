package common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


fun NavController.navigate(route: NavigationRoute, arg: Long? = null){
    val finalRoute = arg?.let {
        route.name + "/$arg"
    } ?: route.name
    navigate(finalRoute)
}
