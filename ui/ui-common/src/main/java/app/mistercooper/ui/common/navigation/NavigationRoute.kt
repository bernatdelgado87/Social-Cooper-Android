package app.mistercooper.ui.common.navigation

interface Route

enum class NavigationRoute: Route {
    HOME_FEED,
    PUBLISH_NOW,
    LOGIN_OR_REGISTER,
    LOGIN,
    REGISTER,
}

enum class BottomSheetRoute: Route {
    COMMENTS
}