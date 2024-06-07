package app.mistercooper.ui.common.navigation

import androidx.navigation.NavHostController

data class GlobalNavigator(
    val nativeController: NavHostController,
    val customNavigator: CustomNavigator
)