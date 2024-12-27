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
        maven(url = "https://jitpack.io")
        maven {
            setUrl("http://maven.aliyun.com/nexus/content/repositories/releases")
            isAllowInsecureProtocol = true
        }
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

rootProject.name = "GLib"
include(":app")
include(":glib")
 