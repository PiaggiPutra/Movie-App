plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
}

apply(from = "${project.rootDir}/android_commons.gradle")

android {

    flavorDimensions.add(AndroidConfig.FLAVOR_DIMENSIONS)

    defaultConfig {
        applicationId = AndroidConfig.ID
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
//            signingConfig = signingConfigs.getByName("defaultSigning")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isShrinkResources = BuildTypeRelease.isShrinkResources
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField( "String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField( "String", "ACCESS_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZGI1NmE5MmJkOTA4NmZlN2JlNTVjNGNlZjdlZjBhNCIsInN1YiI6IjVlY2VhNTRlMjFjNGNhMDAyMWRiZTJmYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.d-zGf7OqSijv07feKjYYArrKsZ6J8KJgTjz0knA66x0\"")


        }
        getByName(BuildType.DEBUG) {
//            signingConfig = signingConfigs.getByName("defaultSigning")
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            buildConfigField( "String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField( "String", "ACCESS_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZGI1NmE5MmJkOTA4NmZlN2JlNTVjNGNlZjdlZjBhNCIsInN1YiI6IjVlY2VhNTRlMjFjNGNhMDAyMWRiZTJmYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.d-zGf7OqSijv07feKjYYArrKsZ6J8KJgTjz0knA66x0\"")
        }
    }

    productFlavors {
        create(ProductFlavor.DEVELOPMENT) {
            applicationIdSuffix = ".debug"
            dimension = AndroidConfig.FLAVOR_DIMENSIONS
            resValue("string", "app_name", "Movie App")
        }
        create(ProductFlavor.PRODUCTION) {
            applicationId = AndroidConfig.ID
            resValue("string", "app_name", "Movie App")
        }
    }

}

dependencies {
    /** Features Module */
    implementation(project(Modules.features))

    /** Common Module */
    implementation(project(Modules.common))

    /** Data Module */
    implementation(project(Modules.local))
    implementation(project(Modules.remote))
    implementation(project(Modules.repository))

    /** Navigation Module */
    implementation(project(Modules.navigation))

    /** Navigation Dependencies */
    implementation(NavigationDependencies.navigationUi)
    implementation(NavigationDependencies.navigationFragment)

    /** Koin Dependencies */
    implementation(KoinDependencies.koin)
    implementation(KoinDependencies.koinViewodel)

    /** AndroidX Support Dependencies */
    implementation(AndroidXSupportDependencies.constraint)
    implementation(AndroidXSupportDependencies.appcompat)

    /** Utils Dependencies */
    detektPlugins(UtilsDependencies.detektFormatting)

}
