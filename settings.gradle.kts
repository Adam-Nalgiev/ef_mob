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

rootProject.name = "Thousands Courses"
include(":app")
include(":core:feature-api")
include(":feature:onboarding:onboarding-api")
include(":feature:onboarding:onboarding-impl")
include(":feature:authorization:authorization-api")
include(":feature:authorization:authorization-impl")
include(":feature:menu:menu-api")
include(":feature:menu:menu-impl")
include(":feature:main:main-api")
include(":feature:main:main-impl")
include(":feature:favorites:favorites-api")
include(":feature:favorites:favorites-impl")
include(":feature:account:account-api")
include(":feature:account:account-impl")
include(":core:datastore")
include(":feature:course-description:course-description-api")
include(":feature:course-description:course-description-impl")
