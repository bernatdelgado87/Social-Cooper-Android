package common.navigation

import androidx.navigation.NavHostController
import common.navigation.CustomNavigator

data class GlobalNavigator(
    val nativeController: NavHostController,
    val customNavigator: CustomNavigator
)