pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "Social Cooper Android"
include(":app")
include(":app:main")
include(":shared")
//shared data
include(":shared:data-shared:data-shared-comment")
include(":shared:data-shared:data-shared-common")
include(":shared:data-shared:data-shared-main")
include(":shared:data-shared")
include(":shared:data-shared:data-shared-common")
include(":shared:data-shared:data-shared-home")
include(":shared:data-shared:data-shared-register-login")
include(":shared:data-shared:data-shared-publish")
//shared domain
include(":shared:domain-shared")
include(":shared:domain-shared:domain-shared-comment")
include(":shared:domain-shared:domain-common")
include(":shared:domain-shared:domain-shared-comment")
include(":shared:domain-shared:domain-shared-home")
include(":shared:domain-shared:domain-shared-register-login")
include(":shared:domain-shared:domain-shared-publish")
include(":shared:domain-shared:domain-shared-common")
//shared ui
include(":shared:ui-shared:ui-main-shared")
include(":shared:ui-shared:ui-home-shared")
include(":shared:ui-shared:ui-publish-shared")
include(":shared:ui-shared:ui-registerlogin-shared")
include(":shared:ui-shared:ui-comment-shared")
//android data
include(":data:data-common")
//android domain
include(":domain:domain-common")
//android ui
include(":shared:ui-shared")
include(":ui:ui-registerLogin")
include(":common-ui")
include(":ui:ui-comment")
include(":ui:ui-common")
include(":ui:ui-home")
include(":ui:ui-publish")
