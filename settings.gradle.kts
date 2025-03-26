pluginManagement {
    repositories {
        google {
            content {
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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SuperFinancer"
include(":app")
include(":core")
include(":main-menu-feature")
include(":navigation")
include(":feed-feature")
include(":web-view-feature")
include(":search-feature")
include(":network")
include(":network")
include(":ui-components")
include(":widgets")
include(":analytics")
