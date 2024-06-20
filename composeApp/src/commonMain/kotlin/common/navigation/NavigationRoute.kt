package common.navigation

interface Route

enum class NavigationRoute: Route {
    HOME_FEED,
    PUBLISH_NOW,
    LOGIN_OR_REGISTER,
    LOGIN,
    REGISTER,
    COMMENTS
}