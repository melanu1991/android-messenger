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

rootProject.name = "Messenger"
include(":app")
include(":templates:android-library")
include(":templates:kotlin-library")
include(":templates:feature:presentation")
include(":templates:feature:domain")
include(":core:essentials")
include(":core:theme")
include(":core:common:android")
include(":features:init:domain")
include(":features:init:presentation")
include(":navigation")
include(":features:signin:domain")
include(":features:signin:presentation")
